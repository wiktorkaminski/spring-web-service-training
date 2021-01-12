document.addEventListener("DOMContentLoaded", function () {

    /**
     * Form Select
     */
    class FormSelect {
        constructor($el) {
            this.$el = $el;
            this.options = [...$el.children];
            this.init();
        }

        init() {
            this.createElements();
            this.addEvents();
            this.$el.parentElement.removeChild(this.$el);
        }

        createElements() {
            // Input for value
            this.valueInput = document.createElement("input");
            this.valueInput.type = "text";
            this.valueInput.name = this.$el.name;

            // Dropdown container
            this.dropdown = document.createElement("div");
            this.dropdown.classList.add("dropdown");

            // List container
            this.ul = document.createElement("ul");

            // All list options
            this.options.forEach((el, i) => {
                const li = document.createElement("li");
                li.dataset.value = el.value;
                li.innerText = el.innerText;

                if (i === 0) {
                    // First clickable option
                    this.current = document.createElement("div");
                    this.current.innerText = el.innerText;
                    this.dropdown.appendChild(this.current);
                    this.valueInput.value = el.value;
                    li.classList.add("selected");
                }

                this.ul.appendChild(li);
            });

            this.dropdown.appendChild(this.ul);
            this.dropdown.appendChild(this.valueInput);
            this.$el.parentElement.appendChild(this.dropdown);
        }

        addEvents() {
            this.dropdown.addEventListener("click", e => {
                const target = e.target;
                this.dropdown.classList.toggle("selecting");

                // Save new value only when clicked on li
                if (target.tagName === "LI") {
                    this.valueInput.value = target.dataset.value;
                    this.current.innerText = target.innerText;
                }
            });
        }
    }

    document.querySelectorAll(".form-group--dropdown select").forEach(el => {
        new FormSelect(el);
    });

    /**
     * Hide elements when clicked on document
     */
    document.addEventListener("click", function (e) {
        const target = e.target;
        const tagName = target.tagName;

        if (target.classList.contains("dropdown")) return false;

        if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
            return false;
        }

        if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
            return false;
        }

        document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
            el.classList.remove("selecting");
        });
    });

    /**
     * Switching between form steps
     */
    class FormSteps {
        constructor(form) {
            this.$form = form;
            this.$next = form.querySelectorAll(".next-step");
            this.$prev = form.querySelectorAll(".prev-step");
            this.$step = form.querySelector(".form--steps-counter span");

            this.currentStep = 1;
            this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
            const $stepForms = form.querySelectorAll("form > div");

            this.slides = [...this.$stepInstructions, ...$stepForms];

            this.filledForm = {
                categories: [],
                quantity: 0,
                institution: null,
                pickupAddress: {},
                pickupDetails: {}
            };

            this.init();
        }

        /**
         * Init all methods
         */
        init() {
            this.events();
            this.updateForm();
        }

        /**
         * All events that are happening in form
         */
        events() {
            // Next step
            this.$next.forEach(btn => {
                btn.addEventListener("click", e => {
                    if (!this.validateFormInputs()) return;
                    e.preventDefault();
                    this.currentStep++;
                    this.updateForm();
                });
            });

            // Previous step
            this.$prev.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    this.currentStep--;
                    this.updateForm();
                });
            });

            // Form submit
            this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
        }

        validateFormInputs() {
            if (this.currentStep === 1) {
                this.readStep1Inputs();
                if (this.filledForm.categories.length === 0) {
                    return this.invalidateCallbackFn("Wybierz co najmniej jedną kategorię.");
                }
            }

            if (this.currentStep === 2) {
                this.readStep2Inputs();
                if (isNaN(this.filledForm.quantity) || this.filledForm.quantity === "") {
                    return this.invalidateCallbackFn("Wprowadź prawidłową liczbę worków.");
                }
                if (this.filledForm.quantity <= 0) {
                    return this.invalidateCallbackFn("Należy podać wartość większą od zera.");
                }
            }

            if (this.currentStep === 3) {
                this.readStep3Inputs();
                if (this.filledForm.institution == null) {
                    return this.invalidateCallbackFn("Wybierz jedną instytucję, której chcesz przekazać dary.")
                }
            }

            if (this.currentStep === 4) {
                this.readStep4Inputs();
                if (this.filledForm.pickupAddress.street === "" ||
                    this.filledForm.pickupAddress.city === "" ||
                    this.filledForm.pickupAddress.zipCode === "" ||
                    this.filledForm.pickupDetails.phone === "" ||
                    this.filledForm.pickupDetails.pickupDate === ""
                ) {
                    return this.invalidateCallbackFn("Wszystkie pola, za wyjątkiem uwag dla kuriera muszą być wypełnione");

                }

                let zipCodeRegEx = /^([0-9]{2}-[0-9]{3})$/;
                if (!zipCodeRegEx.test(this.filledForm.pickupAddress.zipCode)) {
                    return this.invalidateCallbackFn("Niepoprawny kod pocztowy");
                }

                let plCellRegEx = /^((\+48 ?)?[0-9]{3}[- ][0-9]{3}[- ][0-9]{3})$|^((\+48 ?)?[0-9]{9})$/;
                let plLandLineRegEx = /^(\+48 ?)?[0-9]{2}[- ][0-9]{3}[- ][0-9]{2}[- ][0-9]{2}$/;
                if (!((plCellRegEx.test(this.filledForm.pickupDetails.phone)) ||
                    (plLandLineRegEx.test(this.filledForm.pickupDetails.phone)))) {
                    return this.invalidateCallbackFn("Niepoprawny numer telefonu. Możliwe podanie wyłącznie numerów zarejestrowanych w polsce. " +
                        "(opcjonalnie można podać prefix +48)");

                }

                let dateRegEx = /^[0-9]{4}-[0-9]{2}-[0-9]{2}$/
                let currDate = new Date()
                let date = new Date(this.filledForm.pickupDetails.pickupDate);
                if (!dateRegEx.test(this.filledForm.pickupDetails.pickupDate)) {
                    return this.invalidateCallbackFn("Podaj datę w formacie rrrr-mm-dd.")
                }
                if (date < currDate) {
                    return this.invalidateCallbackFn("Podana data musi być w przyszłości.");
                }
            }
            return true;
        }

        invalidateCallbackFn(msg) {
            alert(msg);
            return false;
        }


        /**
         * Update form front-end
         * Show next or previous section etc.
         */
        updateForm() {
            this.$step.innerText = this.currentStep;

            // TODO: Validation

            this.slides.forEach(slide => {
                slide.classList.remove("active");

                if (slide.dataset.step == this.currentStep) {
                    slide.classList.add("active");
                }
            });

            this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
            this.$step.parentElement.hidden = this.currentStep >= 5;

            if (this.currentStep >= 5) {
                this.fillSummary();
            }
        }

        readStep1Inputs() {
            let checkedBoxes = this.$form.querySelectorAll(".checkbox--input:checked");
            this.filledForm.categories.length = 0;
            for (let i = 0; i < checkedBoxes.length; i++) {
                let categoryDescription = checkedBoxes[i].nextElementSibling.nextElementSibling.textContent;
                this.filledForm.categories.push(categoryDescription);
            }
        }

        readStep2Inputs() {
            this.filledForm.quantity = this.$form.querySelector("#quantity").value;
        }

        readStep3Inputs() {
            let checkedRadio = this.$form.querySelector(".form--radio:checked");
            if (checkedRadio != null) {
                this.filledForm.institution = checkedRadio.nextElementSibling.nextElementSibling.firstElementChild.textContent;
            }
        }

        readStep4Inputs() {
            let pickupInputs = this.$form.querySelector(".form-section--columns");
            this.filledForm.pickupAddress.street = pickupInputs.querySelector("#street").value;
            this.filledForm.pickupAddress.city = pickupInputs.querySelector("#city").value;
            this.filledForm.pickupAddress.zipCode = pickupInputs.querySelector("#zipCode").value.trim();
            this.filledForm.pickupDetails.phone = pickupInputs.querySelector("#phone").value.trim();
            this.filledForm.pickupDetails.pickupDate = pickupInputs.querySelector("#pickUpDate").value;
            this.filledForm.pickupDetails.pickupTime = pickupInputs.querySelector("#pickUpTime").value;
            this.filledForm.pickupDetails.pickupComment = pickupInputs.querySelector("#pickUpComment").value;
        }

        fillSummary() {
            let summary = this.$form.querySelector(".summary");
            let summaryText = summary.querySelectorAll(".summary--text");
            let summaryColumns = summary.querySelectorAll(".form-section--column");

            summaryText[0].innerText = this.filledForm.quantity;
            switch (this.filledForm.quantity) {
                case '1':
                    summaryText[0].innerText += " worek";
                    break;
                case '2':
                case '3':
                case '4':
                    summaryText[0].innerText += " worki";
                    break;
                default :
                    summaryText[0].innerText += " worków";
                    break;
            }
            summaryText[0].innerText += ", w których są:"

            for (let i = 0; i < this.filledForm.categories.length; i++) {
                summaryText[0].innerText += " " + this.filledForm.categories[i].toLowerCase();
                if (i !== this.filledForm.categories.length - 1) {
                    summaryText[0].innerText += ",";
                } else {
                    summaryText[0].innerText += ".";
                }
            }

            summaryText[1].innerText = "Dla: " + this.filledForm.institution;

            let summaryColumnLeft = summaryColumns[0].querySelectorAll("li");
            let summaryColumnRight = summaryColumns[1].querySelectorAll("li");

            summaryColumnLeft[0].innerText = this.filledForm.pickupAddress.street;
            summaryColumnLeft[1].innerText = this.filledForm.pickupAddress.city;
            summaryColumnLeft[2].innerText = this.filledForm.pickupAddress.zipCode;
            summaryColumnLeft[3].innerText = this.filledForm.pickupDetails.phone;

            summaryColumnRight[0].innerText = this.filledForm.pickupDetails.pickupDate;
            summaryColumnRight[1].innerText = this.filledForm.pickupDetails.pickupTime;
            summaryColumnRight[2].innerText = this.filledForm.pickupDetails.pickupComment;
        }

    }

    const form = document.querySelector(".form--steps");
    if (form !== null) {
        new FormSteps(form);
    }

    class institutionSlider {
        constructor(slider) {
            this.institutionSlides = slider.querySelectorAll(".help--slides");
            this.prevSlide = slider.querySelector(".prev-step");
            this.nextSlide = slider.querySelector(".next-step");
            this.currentSlideIndex = 1;

            this.init();
        }

        init() {
            this.institutionSlides[0].classList.add("active");
            this.addEventListeners();
            setInterval(this.slideSwitcher, 500);
        }

        addEventListeners() {
            this.prevSlide.addEventListener("click", e => {
                if (--this.currentSlideIndex < 1) {
                    console.log(this.institutionSlides.length)
                    this.currentSlideIndex = this.institutionSlides.length
                }
                this.updateSlides();
            });

            this.nextSlide.addEventListener("click", e => {
                if (++this.currentSlideIndex > this.institutionSlides.length) {
                    this.currentSlideIndex = 1;
                }
                this.updateSlides();
            });
        }

        updateSlides() {
            this.institutionSlides.forEach(slide => {
                slide.classList.remove("active");
                if (parseInt(slide.dataset.id) === this.currentSlideIndex) {
                    slide.classList.add("active");
                }
            });
        }
    }

    const institSlider = document.querySelector(".help");
    if (institSlider !== null) {
        new institutionSlider(institSlider);
    }
});

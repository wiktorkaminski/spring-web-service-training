<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form modelAttribute="donation" method="POST">
    <form:checkboxes path="categories"
                     items="${categories}" itemLabel="name" itemValue="id"/>
    <form:radiobuttons path="institution.id" items="${institutions}" itemLabel="name" itemValue="id"/>
    <form:input path="zipCode"/>
    <form:input path="street"/>
    <form:input path="city"/>
    <form:input path="quantity"/>
    <form:textarea path="pickUpComment"/>
    <form:input type="date" path="pickUpDate"/>
    <form:input type="time" path="pickUpTime"/>
    <input type="submit" value="zapisz">
</form:form>
</body>
</html>

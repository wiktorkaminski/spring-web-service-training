<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
  <head>
    <%@include file="/WEB-INF/jspf/head.jspf"%>
  </head>
  <body>
    <header>

      <nav class="container container--70">
        <%@include file="/WEB-INF/jspf/nav-top-login-register-bar.jspf" %>
        <%@include file="/WEB-INF/jspf/nav-top-nav-bar.jspf" %>
      </nav>

    </header>

    <section class="login-page">
      <h2>Zaloguj się</h2>
      <form method="POST" id="loginForm">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="form-group">
          <input type="email" name="email" id="email" placeholder="Email" />
        </div>
        <div class="form-group">
          <input type="password" name="password" id="password" placeholder="Hasło" />
          <a href="#" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>
        </div>

        <div class="form-group form-group--buttons">
          <a href="#" class="btn btn--without-border">Załóż konto</a>      
          <button class="btn" type="submit">Zaloguj się</button> 
        </div>
      </form>
    </section>

    <footer>
      <%@include file="/WEB-INF/jspf/footer-contact-div.jspf" %>
      <%@include file="/WEB-INF/jspf/footer-bottom-line-div.jspf" %>
      </footer>
  </body>
</html>

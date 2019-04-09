<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-dark bg-dark justify-content-between">
    <a class="navbar-brand" href="<c:url value="/"></c:url>">COMPS380F Web Applications: Design and Development</a>
    <ul class="navbar-nav">
        <security:authorize access="isAnonymous()">
            <li class="nav-item">
                <a class="nav-link icon-wrapper" href="<c:url value="/login"></c:url>">
                    <span class="icon"><i class="fas fa-sign-in-alt"></i></span>
                    <span>Login</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link icon-wrapper" href="<c:url value="/register"></c:url>">
                    <span class="icon"><i class="fas fa-user-plus icon" ></i></span>
                    <span>Register</span>
                </a>
            </li>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <security:authorize access="hasRole('LECTURER')">
                <li class="nav-item">
                    <a class="nav-link icon-wrapper" href="<c:url value="/user/"></c:url>">
                        <span class="icon"><i class="fas fa-users icon"></i></span>
                        <span>User List</span>
                    </a>
                </li>
            </security:authorize>
            <li class="nav-item">
                <a class="nav-link icon-wrapper" href="#">
                    <span class="icon"><i class="fas fa-user icon"></i></span>
                    <span><security:authentication property="principal.username" /></span>
                </a>
            </li>
            <li class="nav-item">
                <c:url var="logoutUrl" value="/logout"/>
                <form id="logout-form" action="${logoutUrl}" action="/logout" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
                <a class="nav-link icon-wrapper" href="#" onclick="$('#logout-form').submit()">
                    <span class="icon"><i class="fas fa-sign-out-alt icon"></i></span>
                    <span>Logout</span>
                </a>
            </li>
        </security:authorize>
    </ul>
</nav>
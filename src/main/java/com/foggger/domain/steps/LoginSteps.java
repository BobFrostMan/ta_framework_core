package com.foggger.domain.steps;

public interface LoginSteps extends BaseSteps {

    /**
     * Opens index page
     *
     * @return - LoginSteps object
     */
    LoginSteps openSite();

    /**
     * Performs login with provided username and password
     *
     * @param user - username to use
     * @param pass - password to for username
     * @return - LoginSteps object
     */
    LoginSteps loginAs(String user, String pass);

    /**
     * Returns true if user is logged in.
     *
     * @return true if user is logged in.
     */
    boolean isUserLoggedIn();

}

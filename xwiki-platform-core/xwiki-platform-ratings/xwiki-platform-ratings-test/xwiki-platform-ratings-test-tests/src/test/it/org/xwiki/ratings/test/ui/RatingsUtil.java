package org.xwiki.ratings.test.ui;

import java.util.List;
import java.util.Random;

import org.xwiki.ratings.test.po.RatingConfigPage;
import org.xwiki.test.ui.po.ViewPage;

public class RatingsUtil extends ViewPage
{
    private static RatingsUtil instance;

    private RatingsUtil()
    {
        // dummy
    }

    /**
     * Get singleton instance
     * 
     * @return the instance of RatingsUtil
     */
    public static RatingsUtil getInstance()
    {
        if (instance == null) {
            instance = new RatingsUtil();
        }

        return instance;
    }
    
    /**
     * Login with a certain user
     * 
     * @param username the username of the user
     */
    public void login(String username)
    {
        login(username, "pass");
    }

    /**
     * Login with a certain user
     * 
     * @param username the username of the user
     * @param password the password of the user
     */
    public void login(String username, String password)
    {
        if (username.equals("guest")) {
            getUtil().forceGuestUser();
            return;
        }

        getDriver().get(getUtil().getURLToLoginAndGotoPage(username, password, getUtil().getURLToNonExistentPage()));
        getUtil().recacheSecretToken();
    }

    /**
     * Configure global ratings
     * 
     * @param configuration
     */
    public void configureGlobal(RatingsConfiguration configuration)
    {
        configure(true, null, configuration);
    }

    /**
     * Configure space ratings
     * 
     * @param space
     * @param configuration
     */
    public void configureSpace(String space, RatingsConfiguration configuration)
    {
        configure(false, space, configuration);
    }

    /**
     * Configure ratings
     * 
     * @param global
     * @param space
     * @param configuration
     */
    private void configure(Boolean global, String space, RatingsConfiguration configuration)
    {
        RatingConfigPage configurationPage =
            global ? RatingConfigPage.getGlobalConfig() : RatingConfigPage.getSpaceConfig(space);

        if (!configurationPage.hasRatingConfigObject()) {
            configurationPage.addRatingConfigurationObject();
        }

        configurationPage.setDisplayRatings(configuration.getDisplayRatings());
        configurationPage.setExcludedPages(configuration.getExcludedPages());
        configurationPage.setStorageMethod(configuration.getStorageMethod());
        configurationPage.setStorageSpace(configuration.getStorageSpace());
        configurationPage.setStoreInSeparateSpaces(configuration.getStorageSeparateSpaces());
        configurationPage.setStoreAverageRating(configuration.getStoreAverageRating());
        configurationPage.setReputation(configuration.getReputation());
        configurationPage.setReputationStored(configuration.getReputationStored());
        configurationPage.setReputationMethod(configuration.getReputationMethod());
        configurationPage.setReputationAlgorithm(configuration.getReputationAlgorithm());
        configurationPage.setReputationCustomAlgorithm(configuration.getReputationCustomAlgorithm());

        configurationPage.clickSaveAndView();
    }

    /**
     * Create pages
     * 
     * @param space the space in which to create the pages
     * @param pages list of pages to create
     */
    public void createPages(String space, List<String> pages)
    {
        for (String page : pages) {
            if (getUtil().pageExists(space, page)) {
                getUtil().deletePage(space, page);
            }

            getUtil().createPage(space, page, "", page);
        }
    }
    
    /**
     * Random integer
     * 
     * @param min
     * @param max
     * @return
     */
    public int randInt(int min, int max)
    {
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    
    /**
     * Round decimal number to specified precision
     * 
     * @param value
     * @param precision
     * @return
     */
    public double round(double value, int precision)
    {
        precision = Integer.parseInt("1" + (new String(new char[precision]).replace("\0", "0")));
        
        return Math.floor(value * precision + .5)/precision;
    }
}

/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.ratings.test.po;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.xwiki.test.ui.po.InlinePage;

public class RatingConfigPage extends InlinePage
{
    private RatingUtil ratingUtil = new RatingUtil();
    
    private final String objectPropertyPrefix = "XWiki.RatingsConfigClass_0_";

    @FindBy(id = objectPropertyPrefix + "displayRatings")
    private WebElement displayRatingsElement;

    @FindBy(id = objectPropertyPrefix + "excludedPages")
    private WebElement excludedPagesElement;

    @FindBy(id = objectPropertyPrefix + "managerHint")
    private WebElement managerHintElement;

    @FindBy(id = objectPropertyPrefix + "storageSpace")
    private WebElement storageSpaceElement;

    @FindBy(id = objectPropertyPrefix + "storageSeparateSpaces")
    private WebElement storageSeparateSpacesElement;

    @FindBy(id = objectPropertyPrefix + "storeAverageRating")
    private WebElement storeAverageRatingElement;

    @FindBy(id = objectPropertyPrefix + "reputation")
    private WebElement reputationElement;

    @FindBy(id = objectPropertyPrefix + "reputationStored")
    private WebElement reputationStoredElement;

    @FindBy(id = objectPropertyPrefix + "reputationMethod")
    private WebElement reputationMethodElement;

    @FindBy(id = objectPropertyPrefix + "reputationAlgorithm")
    private WebElement reputationAlgorithmElement;

    @FindBy(id = objectPropertyPrefix + "reputationCustomAlgorithm")
    private WebElement reputationCustomAlgorithmElement;
    
    @FindBy(xpath = "//div[@id='add_xobject']/select[@id='classname']")
    private WebElement classNameElement;
    
    @FindBy(xpath = "//div[@id='add_xobject']/descendant::input[@type='submit']")
    private WebElement classAddButtonElement;

    /**
     * Get the global configuration page
     * 
     * @return the global ratings configuration page
     */
    public static RatingConfigPage getGlobalConfig()
    {
        return getConfigPage("", true);
    }

    /**
     * Get a space configuration page
     * 
     * @param space the space for the configuration
     * @return the ratings configuration page of the requested space
     */
    public static RatingConfigPage getSpaceConfig(String space)
    {
        return getConfigPage(space, false);
    }

    /**
     * Get configuration page
     * 
     * @param space the space for the configuration
     * @param global retrieve global configuration page (when true, space is ignored)
     * @return the ratings configuration page of the requested space or the global one
     */
    public static RatingConfigPage getConfigPage(String space, Boolean global)
    {
        getUtil().gotoPage(global ? "XWiki" : space, global ? "RatingsConfig" : "WebPreferences", "edit",
            "editor=object");

        return new RatingConfigPage();
    }
    
    /**
     * Has rating configuration object
     * 
     * @return
     */
    public Boolean hasRatingConfigObject()
    {
        return ratingUtil.elementExists(By.id("xobject_XWiki.RatingsConfigClass_0"));
    }
    
    /**
     * Add rating configuration object
     */
    public void addRatingConfigurationObject()
    {
        Select classNameSelect = new Select(classNameElement);
        
        classNameSelect.selectByValue("XWiki.RatingsConfigClass");

        classAddButtonElement.click();
    }

    /**
     * Get display ratings
     * 
     * @return
     */
    public Boolean getDisplayRatings()
    {
        Select displayRatingsSelect = new Select(this.displayRatingsElement);

        return displayRatingsSelect.getFirstSelectedOption().getAttribute("value") == "1";
    }

    /**
     * Set display value
     * 
     * @param display
     */
    public void setDisplayRatings(Boolean display)
    {
        Select displayRatingsSelect = new Select(this.displayRatingsElement);

        displayRatingsSelect.selectByValue(display ? "1" : "0");
    }

    /**
     * Get excluded pages
     * 
     * @return
     */
    public List<String> getExcludedPages()
    {
        String rawValue = this.excludedPagesElement.getAttribute("value");

        return Arrays.asList(rawValue.split(","));
    }

    /**
     * Set excluded pages
     * 
     * @param excludedPages
     */
    public void setExcludedPages(List<String> excludedPages)
    {
        this.excludedPagesElement.clear();
        this.excludedPagesElement.sendKeys(StringUtils.join(excludedPages, ","));
    }

    /**
     * Get storage method
     * 
     * @return
     */
    public String getStorageMethod()
    {
        Select managerHintSelect = new Select(this.managerHintElement);

        return managerHintSelect.getFirstSelectedOption().getAttribute("value");
    }

    /**
     * Set storage method
     * 
     * @param managerHint
     */
    public void setStorageMethod(String managerHint)
    {
        Select managerHintSelect = new Select(this.managerHintElement);

        managerHintSelect.selectByValue(managerHint);
    }

    /**
     * Get storage space
     * 
     * @return
     */
    public String getStorageSpace()
    {
        return this.storageSpaceElement.getAttribute("value");
    }

    /**
     * Set storage space
     * 
     * @param space
     */
    public void setStorageSpace(String space)
    {
        this.storageSpaceElement.clear();
        this.storageSpaceElement.sendKeys(space);
    }

    /**
     * Get store in separate spaces
     * 
     * @return
     */
    public Boolean getStoraInSeparateSpaces()
    {
        Select storageSeparateSpacesSelect = new Select(this.storageSeparateSpacesElement);

        return storageSeparateSpacesSelect.getFirstSelectedOption().getAttribute("value") == "1";
    }

    /**
     * Set store in separate spaces
     * 
     * @param storeInSeparateSpaces
     */
    public void setStoreInSeparateSpaces(Boolean storeInSeparateSpaces)
    {
        Select storageSeparateSpacesSelect = new Select(this.storageSeparateSpacesElement);

        storageSeparateSpacesSelect.selectByValue(storeInSeparateSpaces ? "1" : "0");
    }

    /**
     * Get store average rating
     * 
     * @return
     */
    public Boolean getStoreAverageRating()
    {
        Select storeAverageRatingSelect = new Select(this.storeAverageRatingElement);

        return storeAverageRatingSelect.getFirstSelectedOption().getAttribute("value") == "1";
    }

    /**
     * Set store average rating
     * 
     * @param storeAverageRating
     */
    public void setStoreAverageRating(Boolean isAverageRatingStored)
    {
        Select storeAverageRatingSelect = new Select(this.storeAverageRatingElement);

        storeAverageRatingSelect.selectByValue(isAverageRatingStored ? "1" : "0");
    }

    /**
     * Get reputation
     * 
     * @return
     */
    public Boolean getReputation()
    {
        Select reputationSelect = new Select(this.reputationElement);

        return reputationSelect.getFirstSelectedOption().getAttribute("value") == "1";
    }

    /**
     * Set reputation
     * 
     * @param reputation
     */
    public void setReputation(Boolean reputation)
    {
        Select reputationSelect = new Select(this.reputationElement);

        reputationSelect.selectByValue(reputation ? "1" : "0");
    }

    /**
     * Get reputation stored
     * 
     * @return
     */
    public Boolean getReputationStored()
    {
        Select reputationStoredSelect = new Select(this.reputationStoredElement);

        return reputationStoredSelect.getFirstSelectedOption().getAttribute("value") == "1";
    }

    /**
     * Set reputation stored
     * 
     * @param isReputationStored
     */
    public void setReputationStored(Boolean isReputationStored)
    {
        Select reputationStoredSelect = new Select(this.reputationStoredElement);

        reputationStoredSelect.selectByValue(isReputationStored ? "1" : "0");
    }
    
    /**
     * Get reputation method
     * 
     * @return
     */
    public String getReputationMethod()
    {
        return this.reputationMethodElement.getAttribute("value");
    }
    
    /**
     * Set reputation method
     * 
     * @param method
     */
    public void setReputationMethod(String method)
    {
        this.reputationMethodElement.clear();
        this.reputationMethodElement.sendKeys(method);
    }
    
    /**
     * Get reputation algorithm
     * 
     * @return
     */
    public String getReputationAlgorithm()
    {
        Select reputationAlgorithmSelect = new Select(this.reputationAlgorithmElement);
        
        return reputationAlgorithmSelect.getFirstSelectedOption().getAttribute("value");
    }
    
    /**
     * Set reputation algorithm
     * 
     * @param algorithm
     */
    public void setReputationAlgorithm(String algorithm)
    {
        Select reputationAlgorithmSelect = new Select(this.reputationAlgorithmElement);
        
        reputationAlgorithmSelect.selectByValue(algorithm);
    }
    
    /**
     * Get custom reputation algorithm
     * 
     * @return
     */
    public String getReputationCustomAlgorithm()
    {
        return this.reputationCustomAlgorithmElement.getAttribute("value");
    }
    
    /**
     * Set reputation custom algorithm
     * 
     * @param algorithm
     */
    public void setReputationCustomAlgorithm(String algorithm)
    {
        this.reputationCustomAlgorithmElement.clear();
        this.reputationCustomAlgorithmElement.sendKeys(algorithm);
    }
}

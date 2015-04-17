package org.xwiki.ratings.test.ui;

import java.util.List;

public class RatingsConfiguration
{
    private Boolean displayRatings;

    private List<String> excludedPages;

    private String storageMethod;

    private String storageSpace;

    private Boolean storageSeparateSpaces;

    private Boolean storeAverageRating;

    private Boolean reputation;

    private Boolean reputationStored;

    private String reputationMethod;

    private String reputationAlgorithm;

    private String reputationCustomAlgorithm;

    /**
     * Constructor
     * 
     * @param displayRatings
     * @param excludedPages
     * @param storageMethod
     * @param storageSpace
     * @param storageSeparateSpaces
     * @param storeAverageRating
     * @param reputation
     * @param reputationStored
     * @param reputationMethod
     * @param reputationAlgorithm
     * @param reputationCustomAlgorithm
     */
    public RatingsConfiguration(Boolean displayRatings, List<String> excludedPages, String storageMethod,
        String storageSpace, Boolean storageSeparateSpaces, Boolean storeAverageRating, Boolean reputation,
        Boolean reputationStored, String reputationMethod, String reputationAlgorithm, String reputationCustomAlgorithm)
    {
        this.displayRatings = displayRatings;
        this.excludedPages = excludedPages;
        this.storageMethod = storageMethod;
        this.storageSpace = storageSpace;
        this.storageSeparateSpaces = storageSeparateSpaces;
        this.storeAverageRating = storeAverageRating;
        this.reputation = reputation;
        this.reputationStored = reputationStored;
        this.reputationMethod = reputationMethod;
        this.reputationAlgorithm = reputationAlgorithm;
        this.reputationCustomAlgorithm = reputationCustomAlgorithm;
    }

    /**
     * @return the displayRatings
     */
    public Boolean getDisplayRatings()
    {
        return displayRatings;
    }

    /**
     * @param displayRatings the displayRatings to set
     */
    public void setDisplayRatings(Boolean displayRatings)
    {
        this.displayRatings = displayRatings;
    }

    /**
     * @return the excludedPages
     */
    public List<String> getExcludedPages()
    {
        return excludedPages;
    }

    /**
     * @param excludedPages the excludedPages to set
     */
    public void setExcludedPages(List<String> excludedPages)
    {
        this.excludedPages = excludedPages;
    }

    /**
     * @return the storageMethod
     */
    public String getStorageMethod()
    {
        return storageMethod;
    }

    /**
     * @param storageMethod the storageMethod to set
     */
    public void setStorageMethod(String storageMethod)
    {
        this.storageMethod = storageMethod;
    }

    /**
     * @return the storageSpace
     */
    public String getStorageSpace()
    {
        return storageSpace;
    }

    /**
     * @param storageSpace the storageSpace to set
     */
    public void setStorageSpace(String storageSpace)
    {
        this.storageSpace = storageSpace;
    }

    /**
     * @return the storageSeparateSpaces
     */
    public Boolean getStorageSeparateSpaces()
    {
        return storageSeparateSpaces;
    }

    /**
     * @param storageSeparateSpaces the storageSeparateSpaces to set
     */
    public void setStorageSeparateSpaces(Boolean storageSeparateSpaces)
    {
        this.storageSeparateSpaces = storageSeparateSpaces;
    }

    /**
     * @return the storeAverageRating
     */
    public Boolean getStoreAverageRating()
    {
        return storeAverageRating;
    }

    /**
     * @param storeAverageRating the storeAverageRating to set
     */
    public void setStoreAverageRating(Boolean storeAverageRating)
    {
        this.storeAverageRating = storeAverageRating;
    }

    /**
     * @return the reputation
     */
    public Boolean getReputation()
    {
        return reputation;
    }

    /**
     * @param reputation the reputation to set
     */
    public void setReputation(Boolean reputation)
    {
        this.reputation = reputation;
    }

    /**
     * @return the reputationStored
     */
    public Boolean getReputationStored()
    {
        return reputationStored;
    }

    /**
     * @param reputationStored the reputationStored to set
     */
    public void setReputationStored(Boolean reputationStored)
    {
        this.reputationStored = reputationStored;
    }

    /**
     * @return the reputationMethod
     */
    public String getReputationMethod()
    {
        return reputationMethod;
    }

    /**
     * @param reputationMethod the reputationMethod to set
     */
    public void setReputationMethod(String reputationMethod)
    {
        this.reputationMethod = reputationMethod;
    }

    /**
     * @return the reputationAlgorithm
     */
    public String getReputationAlgorithm()
    {
        return reputationAlgorithm;
    }

    /**
     * @param reputationAlgorithm the reputationAlgorithm to set
     */
    public void setReputationAlgorithm(String reputationAlgorithm)
    {
        this.reputationAlgorithm = reputationAlgorithm;
    }

    /**
     * @return the reputationCustomAlgorithm
     */
    public String getReputationCustomAlgorithm()
    {
        return reputationCustomAlgorithm;
    }

    /**
     * @param reputationCustomAlgorithm the reputationCustomAlgorithm to set
     */
    public void setReputationCustomAlgorithm(String reputationCustomAlgorithm)
    {
        this.reputationCustomAlgorithm = reputationCustomAlgorithm;
    }

}

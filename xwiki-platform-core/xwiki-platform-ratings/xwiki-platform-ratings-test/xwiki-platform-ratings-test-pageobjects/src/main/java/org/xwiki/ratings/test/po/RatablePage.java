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

import org.openqa.selenium.By;
import org.xwiki.test.ui.po.ViewPage;

public class RatablePage extends ViewPage
{
    private RatingUtil ratingUtil = new RatingUtil();
    
    /**
     * Go to page
     * 
     * @param space the space name of a document
     * @param page the page name of a document
     * @return the view page of the requested document
     */
    public static RatablePage gotoPage(String space, String page)
    {
        getUtil().gotoPage(space, page);
        
        return new RatablePage();
    }
    
    /**
     * Has rating
     * 
     * @return if the rating element is present on the page or not
     */
    public boolean hasRating()
    {
        return ratingUtil.elementExists(By.className("rating-wrapper"));
    }
    
    /**
     * Get the rating element
     * 
     * @return an instance of the Rating class
     */
    public Rating getRating()// throws RatingNotPresentException
    {
        if(!hasRating())
        {
            //throw new RatingNotPresentException("The rating element is not present.");
        }
        
        return new Rating();
    }
}

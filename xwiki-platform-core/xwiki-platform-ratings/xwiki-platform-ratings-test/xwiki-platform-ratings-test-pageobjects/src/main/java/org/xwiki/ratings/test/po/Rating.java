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
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.xwiki.test.ui.po.BaseElement;

public class Rating extends BaseElement
{
    @FindBy(className = "rating-wrapper")
    private WebElement ratingWrapperElement;
    
    @FindBy(className = "rating-stars")
    private WebElement ratingStarsElement;
    
    @FindBy(className = "rating-message")
    private WebElement ratingMessageElement;
    
    /**
     * Get number of votes
     * 
     * @return
     */
    public int getNumberOfVotes()
    {
        return Integer.parseInt(ratingMessageElement.findElement(By.tagName("span")).getText());
    }
    
    /**
     * Get average rating
     * 
     * @return
     */
    public double getAverageRating()
    {
        double average = Double.parseDouble(ratingStarsElement.findElement(By.className("current-rating")).getCssValue("width"));
        
        return average/100;
    }
    
    /**
     * Set rating 
     * 
     * @param value the number of stars to give
     */
    public void setRating(int value)
    {
        List<String> stars = new LinkedList<String>(Arrays.asList("one", "two", "three", "four", "five"));
        
        ratingStarsElement.findElement(By.className(stars.get(--value) + "-star" + (value > 1 ? "s":""))).click();
    }
}

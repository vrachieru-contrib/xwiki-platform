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
package org.xwiki.ratings.test.ui;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.xwiki.ratings.test.po.RatablePage;
import org.xwiki.ratings.test.po.Rating;
import org.xwiki.test.ui.AbstractTest;
import org.xwiki.test.ui.SuperAdminAuthenticationRule;

public class RatingsTest extends AbstractTest
{
    private RatingsUtil ratingsUtil = RatingsUtil.getInstance();

    private static List<String> users = Arrays.asList("Alpha", "Beta", "Gamma", "Delta", "Epsilon", "Victor");

    @Rule
    public SuperAdminAuthenticationRule superAdmin = new SuperAdminAuthenticationRule(getUtil());

    @BeforeClass
    public static void createUsers()
    {
        for (String user : users) {
            if (!getUtil().pageExists("XWiki", user)) {
                getUtil().createUser(user, "pass", "email", "dummy." + user.toLowerCase() + "@xwiki.org", "first_name", user);
            }
        }
    }

    @Test
    public void testInheritedConfiguration()
    {
        String space = "Inherited";
        String page = "Alpha";

        // Create page
        getUtil().createPage(space, page, "This space inherits the global configuration.",
            "Inherited configuration test");

        // View the newly created page
        RatablePage testPage = RatablePage.gotoPage(space, page);

        // The ratings on this space should be deactivated by inheritance from the global configuration
        Assert.assertFalse(testPage.hasRating());
    }

    @Test
    public void testExcludedPages()
    {
        String space = "Exclusions";
        List<String> pages = Arrays.asList("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        List<String> exclusions = Arrays.asList(pages.get(0), pages.get(2), pages.get(4));

        RatablePage ratablePage;
        Boolean hasRating;

        RatingsConfiguration configuration =
            new RatingsConfiguration(true, exclusions, "default", "", false, false, false, false, "average", "default",
                "");

        // Configure ratings
        ratingsUtil.configureSpace(space, configuration);

        // Create pages
        ratingsUtil.createPages(space, pages);

        // Test exclusions
        for (String page : pages) {
            ratablePage = RatablePage.gotoPage(space, page);
            hasRating = ratablePage.hasRating();

            if (exclusions.contains(page)) {
                Assert.assertFalse(hasRating);
            } else {
                Assert.assertTrue(hasRating);
            }
        }
    }

    @Test
    public void testOnPageVotes()
    {
        String space = "OnPage";
        List<String> pages = Arrays.asList("Alpha");

        RatingsConfiguration configuration =
            new RatingsConfiguration(true, null, "default", "", false, false, false, false, "average", "default", "");

        // Configure ratings
        ratingsUtil.configureSpace(space, configuration);

        // Create pages
        ratingsUtil.createPages(space, pages);

        for (String page : pages) {
            
            double ratingAverageVote = 0;
            double expectedAverageVote = 0;
            
            for (String user : users) {
                ratingsUtil.login(user);

                RatablePage ratablePage = RatablePage.gotoPage(space, page);
                Rating rating = ratablePage.getRating();
                
                int vote = ratingsUtil.randInt(1, 5);
                expectedAverageVote += vote;

                rating.setRating(vote);
            }

            RatablePage ratablePage = RatablePage.gotoPage(space, page);
            Rating rating = ratablePage.getRating();
            
            ratingAverageVote = ratingsUtil.round(rating.getAverageRating(), 1);
            
            expectedAverageVote /= users.size();
            expectedAverageVote = ratingsUtil.round(expectedAverageVote, 1);
            
            
            Assert.assertEquals(rating.getNumberOfVotes(), users.size());
            Assert.assertEquals(ratingAverageVote, expectedAverageVote);
        }
    }

    //@Test
    public void testSeparatePageVotes()
    {
        String space = "";
        List<String> pages = Arrays.asList("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        RatingsConfiguration configuration =
            new RatingsConfiguration(true, null, "separate", "SeparatePageRatings", true, false, false, false,
                "average", "default", "");

        // Configure ratings
        ratingsUtil.configureSpace("SeparatePage", configuration);
    }
}

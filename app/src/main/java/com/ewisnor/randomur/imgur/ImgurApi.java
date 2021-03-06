package com.ewisnor.randomur.imgur;

import com.ewisnor.randomur.imgur.model.BasicImages;
import com.ewisnor.randomur.util.HttpHelper;
import com.ewisnor.randomur.util.JsonHelper;
import com.ewisnor.randomur.util.Pair;

import org.apache.http.message.BasicHeader;

import java.io.IOException;

/**
 * Exposes Imgur API endpoints.
 *
 * Created by evan on 2015-01-02.
 */
public class ImgurApi {
    private static String CLIENT_ID = "99f2f0ab1191caa";

    public static final Integer MAX_RANDOM_PAGES = 50;

    /**
     * Get a list of random images from the Imgur API
     * @param page Page number (60 results at a time), up to a max of 50
     * @return A collection of metadata about random images
     * @throws IOException
     */
    public static BasicImages getRandomImageMeta(Integer page) throws IOException {
        if (page > MAX_RANDOM_PAGES) {
            return null;
        }

        Pair<String, Integer> response = HttpHelper.GetJson("https://api.imgur.com/3/gallery/random/random/"
                        + page.toString(),
                new BasicHeader("Authorization", "Client-ID " + CLIENT_ID));

        return JsonHelper.parseJson(response.getFirst(), BasicImages.class);
    }

}

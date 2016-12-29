package com.etermax.test.flickr.model;

import java.io.Serializable;

/**
 * Created by Mendez Fernandez on 28/12/2016.
 */

public class Content implements Serializable {

    String _content;

    public Content(String _content) {
        this._content = _content;
    }

    public String getContent() {
        return _content;
    }
}

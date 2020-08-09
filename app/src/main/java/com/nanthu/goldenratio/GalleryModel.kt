package com.nanthu.goldenratio

import java.io.Serializable

class GalleryModel : Serializable{

    var img : Int? = null

    constructor(img : Int){
        this.img = img
    }
}
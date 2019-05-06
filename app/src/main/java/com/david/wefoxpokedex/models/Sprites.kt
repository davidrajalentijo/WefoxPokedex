package com.david.wefoxpokedex.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Sprites(@SerializedName("front_default") @Expose var front_image: String)
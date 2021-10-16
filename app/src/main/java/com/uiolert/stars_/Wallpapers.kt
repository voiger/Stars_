package com.uiolert.stars_

import android.service.wallpaper.WallpaperService
import com.uiolert.stars_.engine.EngineWallpaper

class Wallpapers: WallpaperService() {

    override fun onCreateEngine() = StarsWallpaper()

    inner class StarsWallpaper : Engine() {
        val engine = EngineWallpaper(surfaceHolder)
    }
}

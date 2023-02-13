package com.example.aplikasiklinik.view.navigation

import com.example.aplikasiklinik.R

sealed class Routes(val route:String, val icon:Int = 0) {
    object Splash:Routes("splashscreen")
    object Onboarding:Routes("onboarding")
    object Register:Routes("register")
    object Login:Routes("login")
    object Otp:Routes("otp")
    object HomeAntrian:Routes("home_antrian", R.drawable.queue_menu_icon_)
    object ScheduleAntrian:Routes("schedule_antrian", R.drawable.schedule_menu_icon)
    object Profile:Routes("profile", R.drawable.profile_menu_icon)
    object Home:Routes("home")
}

package com.example.aplikasiklinik.widget.antrian

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aplikasiklinik.R
import com.example.aplikasiklinik.view.navigation.Routes

@Composable
fun AntrianListMenu(
    navController: NavController) {
    Column(
        modifier = Modifier
            .padding(14.dp)
    ) {
        Text(
            text = stringResource(R.string.antrian_title),
            style = MaterialTheme.typography.h1,
            fontSize = 12.sp,
            color = MaterialTheme.colors.surface
        )

//                                Menu
        Spacer(modifier = Modifier.height(20.dp))

//                                Regist Queue
        AntrianMenu(
            title = stringResource(R.string.title_queue_regist),
            desc = stringResource(R.string.desc_queue_regist),
            color = MaterialTheme.colors.primaryVariant,
            icon = R.drawable.queue_regist_icon
        ) {
            navController.navigate(Routes.FiturRoute.route+"/"+Routes.AntrianRegister.route)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider(
            color = MaterialTheme.colors.surface.copy(0.18F),
            modifier = Modifier
                .clip(RoundedCornerShape(100))
        )

        Spacer(modifier = Modifier.height(20.dp))

//                                Current Queue
        AntrianMenu(
            title = stringResource(R.string.title_current_queue),
            desc = stringResource(R.string.desc_current_queue),
            color = MaterialTheme.colors.primaryVariant,
            icon = R.drawable.current_queue_icon
        ) {
            navController.navigate(Routes.FiturRoute.route+"/"+Routes.CurrentAntrian.route)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider(
            color = MaterialTheme.colors.surface.copy(0.18F),
            modifier = Modifier
                .clip(RoundedCornerShape(100))
        )

        Spacer(modifier = Modifier.height(20.dp))

//                                Queue History
        AntrianMenu(
            title = stringResource(R.string.title_queue_history),
            desc = stringResource(R.string.desc_queue_history),
            color = MaterialTheme.colors.primaryVariant,
            icon = R.drawable.queue_history_icon
        ) {
            navController.navigate(Routes.FiturRoute.route+"/"+Routes.AntrianHistory.route)
        }
    }
}
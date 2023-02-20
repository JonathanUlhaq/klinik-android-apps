package com.example.aplikasiklinik.view.antrianhistory

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplikasiklinik.R
import com.example.aplikasiklinik.components.CustomTopBar
import com.example.aplikasiklinik.components.FiturHeader
import com.example.aplikasiklinik.components.OutlinedTextFields
import com.example.aplikasiklinik.components.SearchField
import com.example.aplikasiklinik.view.navigation.Routes
import com.example.aplikasiklinik.widget.antrianhistory.AntrianHistoryContent
import com.example.aplikasiklinik.widget.jadwal.FontContent

@Composable
fun AntrianHistory(
    navController: NavController
) {
    val dropDown = remember {
        mutableStateOf(false)
    }

    val currentIndex = remember {
        mutableStateOf<Int?>(null)
    }

    val search = remember {
        mutableStateOf("")
    }


    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            CustomTopBar(navController, "Riwayat Antrian")
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it),
            color = Color.Transparent
        ) {
            Column {
                FiturHeader()
                Box(
                    modifier = Modifier
                        .padding(start = 14.dp, end = 14.dp, bottom = 8.dp)
                ) {
                    SearchField(
                        value = search,
                        label = "Cari riwayat tanggal berobat",
                        icon = R.drawable.icon_search,
                        keyboardType = KeyboardType.Text,
                        eventFocus = { /*TODO*/ }) {

                    }
                }
                LazyColumn(content = {
                    items(5) {index ->
                        dropDown.value = index == currentIndex.value
                        AntrianHistoryContent(dropDown.value,index) {
                            if (currentIndex.value == index) {
                                currentIndex.value = null
                            } else {
                                currentIndex.value = it
                            }
                        }
                    }
                })

            }
        }
    }
}



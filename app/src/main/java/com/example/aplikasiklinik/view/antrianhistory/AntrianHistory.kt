package com.example.aplikasiklinik.view.antrianhistory

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplikasiklinik.R
import com.example.aplikasiklinik.components.CustomTopBar
import com.example.aplikasiklinik.components.FiturHeader
import com.example.aplikasiklinik.components.SearchField
import com.example.aplikasiklinik.widget.antrianhistory.AntrianHistoryContent

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
            CustomTopBar(navController, stringResource(id = R.string.title_queue_history))
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
                        label = stringResource(R.string.search_riwayat),
                        icon = R.drawable.icon_search,
                        keyboardType = KeyboardType.Text,
                        eventFocus = { /*TODO*/ }) {

                    }
                }
                LazyColumn(content = {
                    items(5) {index ->
                        dropDown.value = index == currentIndex.value
                        AntrianHistoryContent(dropDown.value,index) { currentIndexed ->
                            if (currentIndex.value == index) {
                                currentIndex.value = null
                            } else {
                                currentIndex.value = currentIndexed
                            }
                        }
                    }
                })
            }
        }
    }
}



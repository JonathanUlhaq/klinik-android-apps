package com.example.aplikasiklinik.view.antrianhistory

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplikasiklinik.R
import com.example.aplikasiklinik.components.BottomConnectionWarning
import com.example.aplikasiklinik.components.CustomTopBar
import com.example.aplikasiklinik.components.FiturHeader
import com.example.aplikasiklinik.components.SearchField
import com.example.aplikasiklinik.utils.networkChecker
import com.example.aplikasiklinik.widget.antrianhistory.AntrianHistoryContent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
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

    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { false })
    val coroutineScope = rememberCoroutineScope()
    val networkConnection = remember {
        mutableStateOf(networkChecker(context))
    }
    if (!networkConnection.value) {
        LaunchedEffect(key1 = Unit) {
            sheetState.show()
        }
    } else {
        LaunchedEffect(key1 = Unit) {
            sheetState.hide()
        }
    }

    ModalBottomSheetLayout(sheetContent = {
        BottomConnectionWarning(
            context = context ,
            iconClick = {
                coroutineScope.launch {
                    networkConnection.value = networkChecker(context)
                    if (!networkConnection.value) {
                        Toast.makeText(context,"Tidak ada koneksi internet", Toast.LENGTH_SHORT).show()
                    }
                }

            }) {
            coroutineScope.launch {
                networkConnection.value = networkChecker(context)
                if (!networkConnection.value) {
                    Toast.makeText(context,"Tidak ada koneksi internet", Toast.LENGTH_SHORT).show()
                }
            }


        }
    },
        sheetState = sheetState,
        sheetBackgroundColor = Color.Transparent,
        scrimColor = Color.Black.copy(0.8F),
        sheetElevation = 0.dp) {
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



}



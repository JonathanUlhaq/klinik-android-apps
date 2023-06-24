package com.example.aplikasiklinik.view.antrianhistory

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
import com.example.aplikasiklinik.widget.antrianhistory.AntrianHistoryShimering
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AntrianHistory(
    navController: NavController,
    vm: RiwayatAnViewModel
) {
    val uiState = vm.uiState.collectAsState().value

    val dropDown = remember {
        mutableStateOf(false)
    }

    val currentIndex = remember {
        mutableStateOf<Int?>(null)
    }

//    val search = remember {
//        mutableStateOf("")
//    }

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

    ModalBottomSheetLayout(
        sheetContent = {
            BottomConnectionWarning(
                context = context,
                iconClick = {
                    coroutineScope.launch {
                        networkConnection.value = networkChecker(context)
                        if (!networkConnection.value) {
                            Toast.makeText(
                                context,
                                "Tidak ada koneksi internet",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }) {
                coroutineScope.launch {
                    networkConnection.value = networkChecker(context)
                    if (!networkConnection.value) {
                        Toast.makeText(context, "Tidak ada koneksi internet", Toast.LENGTH_SHORT)
                            .show()
                    }
                }


            }
        },
        sheetState = sheetState,
        sheetBackgroundColor = Color.Transparent,
        scrimColor = Color.Black.copy(0.8F),
        sheetElevation = 0.dp
    ) {
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
                if (uiState.data != null) {
                    Column {
                        FiturHeader()
//                       Box(
//                           modifier = Modifier
//                               .padding(start = 14.dp, end = 14.dp, bottom = 8.dp)
//                       ) {
//
//                       }
                        Spacer(modifier = Modifier.height(14.dp))
                        if (uiState.data.isNotEmpty()) {
                            LazyColumn(content = {
                                itemsIndexed(uiState.data) { index, item ->
                                    dropDown.value = index == currentIndex.value
                                    AntrianHistoryContent(
                                        dropDown.value,
                                        index,
                                        item
                                    ) { currentIndexed ->
                                        if (currentIndex.value == index) {
                                            currentIndex.value = null
                                        } else {
                                            currentIndex.value = currentIndexed
                                        }
                                    }
                                }
                            })
                        } else {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .wrapContentSize(Alignment.Center)
                            ) {
                                Text(text = "Anda belum memiliki riwayat kunjungan",
                                    style = MaterialTheme.typography.h2,
                                    color = MaterialTheme.colors.surface)
                            }
                        }
                    }
                } else {
                    AntrianHistoryShimering()
                }
            }
        }
    }


}



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
import com.example.aplikasiklinik.widget.jadwal.FontContent

@Composable
fun AntrianHistory(
    navController: NavController
) {
    val dropDown = remember {
        mutableStateOf(false)
    }

    val search = remember {
        mutableStateOf("")
    }

    val iconDrop by animateIntAsState(targetValue = if (dropDown.value) R.drawable.arrow_down else R.drawable.arrow_right)

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
                Box(modifier = Modifier
                    .padding(start = 14.dp, end = 14.dp, bottom = 8.dp)) {
                    SearchField(
                        value = search ,
                        label = "Cari riwayat tanggal berobat" ,
                        icon = R.drawable.icon_search ,
                        keyboardType = KeyboardType.Text ,
                        eventFocus = { /*TODO*/ }) {

                    }
                }
                LazyColumn(content = {
                    items(5) {
                        AntrianHistoryContent(dropDown, iconDrop)
                    }
                })

            }
        }
    }
}

@Composable
fun AntrianHistoryContent(
    dropDown: MutableState<Boolean>,
    iconDrop: Int
) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colors.onBackground,
        modifier = Modifier
            .padding(start = 14.dp, end = 14.dp, top = 8.dp)
            .fillMaxWidth()
            .clickable { dropDown.value = !dropDown.value },
        contentColor = MaterialTheme.colors.primaryVariant
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "01/01/2023",
                    style = MaterialTheme.typography.h2
                )
                IconButton(onClick = { dropDown.value = !dropDown.value }) {
                    Icon(
                        painter = painterResource(id = iconDrop),
                        contentDescription = null,
                        modifier = Modifier
                            .size(14.dp)
                    )
                }
            }
            AnimatedVisibility(visible = dropDown.value) {
                Column {
                    Spacer(modifier = Modifier.height(12.dp))
                    FontContent(
                        title = "Poli : ",
                        desc = "Umum ",
                        size = 16,
                        color = MaterialTheme.colors.onPrimary
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    FontContent(
                        title = "Status : ",
                        desc = "Sudah dilayani",
                        size = 16,
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }
    }
}

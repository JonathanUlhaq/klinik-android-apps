package com.example.aplikasiklinik.widget.antrian

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aplikasiklinik.R
import com.example.aplikasiklinik.model.currentantrian.AntriSekarangResponse

@Composable
fun WidgetInformation(
    antrian: AntriSekarangResponse
) {
    val antrianKurang = remember {
        mutableStateOf(0)
    }
    Row {
        Surface(
            color = MaterialTheme.colors.primary,
            shape = RoundedCornerShape(26.dp),
            modifier = Modifier
                .width(160.dp)
                .height(150.dp)


        ) {
            Column(
                modifier = Modifier
                    .padding(14.dp)
            ) {
                Box {
                    Surface(
                        color = MaterialTheme.colors.onSurface,
                        elevation = 0.dp,
                        shape = CircleShape
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_antrian_pasien),
                            contentDescription = "icon antrian",
                            modifier = Modifier
                                .size(35.dp)
                                .padding(10.dp),
                            tint = MaterialTheme.colors.primary
                        )
                    }

                    if (antrian.kurang_antrian != null) {
                        antrianKurang.value = antrian.kurang_antrian
                        if( antrianKurang.value >= 1) {
                            Box(
                                modifier = Modifier
                                    .offset(y = 20.dp)
                                    .fillMaxWidth()
                                    .wrapContentWidth(Alignment.End)
                            ) {
                                Text(
                                    text =  antrianKurang.value.toString(),
                                    style = MaterialTheme.typography.h1,
                                    fontSize = 44.sp,
                                    color = MaterialTheme.colors.onSurface
                                )
                            }
                        }
                    }

                }

                Spacer(modifier = Modifier.height(26.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = if (antrian.kurang_antrian != null) {
                            if (antrian.kurang_antrian < 1) {
                                "Yuk segera persiapan, giliran kamu !"
                            } else {
                                stringResource(R.string.sisa_antrian)
                            }

                        } else {
                            "Belum mendaftar antrian"
                        },
                        style = MaterialTheme.typography.body1,
                        fontSize = 14.sp,
                        color = MaterialTheme.colors.onSurface
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(6.dp))
        Surface(
            color = MaterialTheme.colors.primary,
            shape = RoundedCornerShape(26.dp),
            modifier = Modifier
                .width(190.dp)
                .height(150.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(14.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = MaterialTheme.colors.onSurface,
                        elevation = 0.dp,
                        shape = CircleShape
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_praktik),
                            contentDescription = null,
                            modifier = Modifier
                                .size(35.dp)
                                .padding(10.dp),
                            tint = MaterialTheme.colors.primary
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.praktik_sekarang),
                        style = MaterialTheme.typography.h1,
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onSurface
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(R.string.doctor_name),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface
                )
                Spacer(modifier = Modifier.height(5.dp))
                Divider(
                    modifier = Modifier
                        .clip(RoundedCornerShape(100)),
                    color = MaterialTheme.colors.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.praktik_umum),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface
                )
                Spacer(modifier = Modifier.height(5.dp))
                Divider(
                    modifier = Modifier
                        .clip(RoundedCornerShape(100)),
                    color = MaterialTheme.colors.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "08.00 - 12.00 WIB",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}
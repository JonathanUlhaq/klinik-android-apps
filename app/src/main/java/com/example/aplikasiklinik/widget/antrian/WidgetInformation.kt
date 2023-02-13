package com.example.aplikasiklinik.widget.antrian

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aplikasiklinik.R

@Composable
fun WidgetInformation() {
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

                    Box(
                        modifier = Modifier
                            .offset(y = 20.dp)
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                    ) {
                        Text(
                            text = "66",
                            style = MaterialTheme.typography.h1,
                            fontSize = 44.sp,
                            color = MaterialTheme.colors.onSurface
                        )
                    }

                }

                Spacer(modifier = Modifier.height(26.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = stringResource(R.string.jumlah_antrian),
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
                    text = "dr. Yosa Angga Oktana",
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
package com.example.aplikasiklinik.components

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.aplikasiklinik.R

@Composable
fun BottomConnectionWarning(
    context:Context,
    iconClick: () -> Unit,
    cobalagi:() -> Unit
) {
    Box {
        Column {
            Box(modifier = Modifier
                .padding(end = 14.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)){
                IconButton(onClick = { iconClick.invoke() }) {
                    Icon(painter = painterResource(id = R.drawable.close_icon),
                        contentDescription = null,
                        tint = MaterialTheme.colors.background,
                        modifier = Modifier
                            .size(30.dp))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(topStart = 45.dp, topEnd = 45.dp),
                color = MaterialTheme.colors.background
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 18.dp, end = 18.dp, top = 16.dp, bottom = 18.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painter = painterResource(id = R.drawable.no_connection_image),
                        contentDescription = null,
                        modifier = Modifier
                            .size(150.dp))
                    Spacer(modifier = Modifier.height(18.dp))
                    Text(text = "Yah tidak ada jaringan koneksi internet, periksa dahulu jaringan internet kamu",
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.surface,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(315.dp))
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ButtonClick(color = MaterialTheme.colors.onBackground  ,
                            text = "Coba Lagi",
                            textColor = MaterialTheme.colors.primary ) {
                            cobalagi.invoke()
                        }

                        ButtonClick(color = MaterialTheme.colors.primary  ,
                            text = "Buka Pengaturan") {
                            val intent = Intent(Settings.ACTION_SETTINGS)
                            context.startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}
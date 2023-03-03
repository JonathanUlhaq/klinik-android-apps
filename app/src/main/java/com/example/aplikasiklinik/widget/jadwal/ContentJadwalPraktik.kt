package com.example.aplikasiklinik.widget.jadwal

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.aplikasiklinik.R

@Composable
fun ContentJadwalPraktik() {
    Surface(
        color = MaterialTheme.colors.background,
        border = BorderStroke(2.dp, MaterialTheme.colors.primaryVariant),
        shape = RoundedCornerShape(14.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Surface(
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(3.dp, MaterialTheme.colors.onPrimary),
                modifier = Modifier
                    .size(84.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.doctor_punjabi),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

            Column {
                FontContent(stringResource(R.string.nama_dokter), stringResource(R.string.dummy_dokter_name))
                Spacer(modifier = Modifier.height(12.dp))
                FontContent(stringResource(R.string.poli), stringResource(R.string.dummy_poli))
                Spacer(modifier = Modifier.height(12.dp))
                FontContent(stringResource(R.string.jam_praktik), stringResource(R.string.dummy_jam_praktik))
            }
        }
    }
}
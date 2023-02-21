package com.example.aplikasiklinik.widget.antrian

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.aplikasiklinik.R

@Composable
fun AnnounceItem() {
    Column {
        Text(
            text = stringResource(R.string.anounce_title),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(R.string.dummy_announce),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(290.dp)
            )
        }
    }
}
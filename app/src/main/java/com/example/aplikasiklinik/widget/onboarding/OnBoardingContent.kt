package com.example.aplikasiklinik.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingContent(
    image:Int,
    title:Int,
    desc:Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .size(350.dp)
                .padding(top = 30.dp, bottom = 20.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.surface,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(id = desc),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.surface,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(350.dp)
        )
    }
}
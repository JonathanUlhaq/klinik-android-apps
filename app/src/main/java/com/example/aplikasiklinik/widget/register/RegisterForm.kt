package com.example.aplikasiklinik.widget.register

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.aplikasiklinik.R
import com.example.aplikasiklinik.components.DatePicker
import com.example.aplikasiklinik.components.DisableInput
import com.example.aplikasiklinik.components.OutlinedTextFields

@Composable
fun RegisForm(
    nik: MutableState<String>,
    hide: MutableState<Boolean>,
    name: MutableState<String>,
    context: Context,
    date: MutableState<String>,
    address: MutableState<String>,
    phone: MutableState<String>,
    click:() -> Unit
) {
    OutlinedTextFields(
        nik,
        label = stringResource(id = R.string.nik),
        R.drawable.nik_icon,
        eventFocus = {
            hide.value = true
        },
        keyboardType = KeyboardType.NumberPassword
    ) {
        hide.value = false
    }
    Spacer(modifier = Modifier.height(8.dp))

    OutlinedTextFields(
        name,
        label = stringResource(id = R.string.nama),
        R.drawable.name_icon,
        eventFocus = {
            hide.value = true
        },
        keyboardType = KeyboardType.Text
    ) {
        hide.value = false
    }
    Spacer(modifier = Modifier.height(8.dp))
    DatePicker(context = context, date = date)
    Spacer(modifier = Modifier.height(2.dp))
    OutlinedTextFields(
        address,
        label = stringResource(id = R.string.alamat),
        R.drawable.location_icon,
        eventFocus = {
            hide.value = true
        },
        keyboardType = KeyboardType.Text
    ) {
        hide.value = false
    }
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        DisableInput()
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedTextFields(
            phone,
            label = stringResource(id = R.string.phone),
            R.drawable.phone_icon,
            eventFocus = {
                hide.value = true
            },
            keyboardType = KeyboardType.NumberPassword
        ) {
            hide.value = false
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = stringResource(id = R.string.sudah_punya_akun),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body2
        )
        Text(text = " "+stringResource(id = R.string.masuk),
            color = MaterialTheme.colors.surface.copy(0.7F),
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .clickable { click.invoke() })
    }
}
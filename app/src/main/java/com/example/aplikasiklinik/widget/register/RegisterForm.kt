package com.example.aplikasiklinik.widget.register

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.aplikasiklinik.R
import com.example.aplikasiklinik.components.DatePicker
import com.example.aplikasiklinik.components.DisableInput
import com.example.aplikasiklinik.components.OutlinedTextFields
import com.example.aplikasiklinik.components.RegistDatePicker

@OptIn(ExperimentalComposeUiApi::class)
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
    val keyboardController = LocalSoftwareKeyboardController.current
    val namaFocus = remember {
        FocusRequester()
    }
    val dateBoolean = remember {
        mutableStateOf(true)
    }
    val phoneFocus = remember {
        FocusRequester()
    }
    val alamatFocus = remember {
        FocusRequester()
    }
    OutlinedTextFields(
        nik,
        label = stringResource(id = R.string.nik),
        R.drawable.nik_icon,
        eventFocus = {
            hide.value = true
        },
        keyboardType = KeyboardType.NumberPassword,
        onDone = {
            namaFocus.requestFocus()
        }
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
        keyboardType = KeyboardType.Text,
        modifier = Modifier
            .focusRequester(namaFocus),
        onDone = {
            dateBoolean.value = false
            keyboardController?.hide()
        }
    ) {
        hide.value = false
    }
    Spacer(modifier = Modifier.height(8.dp))
    RegistDatePicker(context = context, date = date, boolean = dateBoolean ) {
        alamatFocus.requestFocus()
    }
    Spacer(modifier = Modifier.height(2.dp))
    OutlinedTextFields(
        address,
        label = stringResource(id = R.string.alamat),
        R.drawable.location_icon,
        eventFocus = {
            hide.value = true
        },
        keyboardType = KeyboardType.Text,
        onDone = {
            phoneFocus.requestFocus()
        },
        modifier = Modifier
            .focusRequester(alamatFocus)
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
            keyboardType = KeyboardType.NumberPassword,
            modifier = Modifier
                .focusRequester(phoneFocus)
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
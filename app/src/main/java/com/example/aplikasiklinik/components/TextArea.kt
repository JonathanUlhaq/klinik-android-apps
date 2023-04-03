package com.example.aplikasiklinik.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextArea(
    value: MutableState<String>,
    label: String,
    modifier:Modifier = Modifier,
    eventClick:() -> Unit
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(value = value.value,
        onValueChange = { value.value = it },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colors.primaryVariant,
            backgroundColor = MaterialTheme.colors.onBackground,
            unfocusedBorderColor = MaterialTheme.colors.primaryVariant.copy(0.8F),
            focusedBorderColor = MaterialTheme.colors.primaryVariant,
            leadingIconColor = MaterialTheme.colors.primaryVariant,
            focusedLabelColor = MaterialTheme.colors.primaryVariant,
            unfocusedLabelColor = MaterialTheme.colors.primaryVariant,
            cursorColor = MaterialTheme.colors.primaryVariant
        ),
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.h1,
                fontSize = 12.sp
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .imePadding()
        ,
        shape = RoundedCornerShape(12.dp),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
            eventClick.invoke()

        }),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text),
        singleLine = false,
        minLines = 5
    )
}
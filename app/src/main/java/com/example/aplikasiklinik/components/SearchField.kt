package com.example.aplikasiklinik.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchField(
    value: MutableState<String>,
    label: String,
    icon: Int,
    keyboardType: KeyboardType,
    eventFocus:() -> Unit,
    eventUnFocus:() -> Unit
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(value = value.value,
        onValueChange = { value.value = it },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colors.primaryVariant,
            backgroundColor = MaterialTheme.colors.background,
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
        leadingIcon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusEvent {
                if (it.isFocused) {
                    eventFocus.invoke()
                } else {
                    eventUnFocus.invoke()
                }
            }
        ,
        shape = RoundedCornerShape(12.dp),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
        }),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = keyboardType),
        singleLine = true
    )
}
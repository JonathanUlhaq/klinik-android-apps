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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
    fun OutlinedTextFields(
    value: MutableState<String>,
    label: String,
    icon: Int,
    isNIK:Boolean = false,
    isPhone:Boolean = false,
    modifier:Modifier = Modifier,
    keyboardType:KeyboardType,
    onDone:() -> Unit = {},
    error:Boolean = false,
    visualTransforimation:VisualTransformation = VisualTransformation.None,
    trailingIcon:@Composable () -> Unit = {},
    eventFocus:() -> Unit,
    eventUnFocus:() -> Unit
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(value = value.value,
        onValueChange = {
            if (isNIK) {
            if (it.length <= 16) value.value = it.trim()
            } else if (isPhone) {
                if (it.length <= 12) value.value = it.trim()
            } else {
                value.value = it
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            backgroundColor = MaterialTheme.colors.primary,
            unfocusedBorderColor = MaterialTheme.colors.onSurface.copy(0.8F),
            focusedBorderColor = MaterialTheme.colors.onSurface,
            leadingIconColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White,
            cursorColor = Color.White
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
                    .size(16.dp)
            )
        },
        modifier = modifier
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
            onDone.invoke()
        }),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = keyboardType),
        singleLine = true,
        isError = error,
        trailingIcon = {
            trailingIcon.invoke()
        },
        visualTransformation = visualTransforimation
    )
}
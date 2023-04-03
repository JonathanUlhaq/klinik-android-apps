package com.example.aplikasiklinik.widget.profil

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProfilContent(
    icon: Int,
    desc: MutableState<String>,
    boolean: Boolean,
    keyboard: KeyboardType
) {
    val keyboardOption = LocalSoftwareKeyboardController.current
    OutlinedTextField(value = desc.value,
        onValueChange = { desc.value = it },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colors.surface,
            backgroundColor = Color.Transparent,
            disabledBorderColor = MaterialTheme.colors.primaryVariant.copy(0.7F),
            unfocusedBorderColor = MaterialTheme.colors.primaryVariant.copy(0.8F),
            focusedBorderColor = MaterialTheme.colors.primaryVariant,
            unfocusedLabelColor = MaterialTheme.colors.primaryVariant.copy(0.8F),
            focusedLabelColor = MaterialTheme.colors.primaryVariant,
            leadingIconColor = MaterialTheme.colors.primaryVariant,
            disabledLeadingIconColor = MaterialTheme.colors.primaryVariant.copy(0.7F),
            disabledTextColor = MaterialTheme.colors.surface.copy(0.7F)
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
            )
        },
        enabled = boolean,
        keyboardOptions = KeyboardOptions(keyboardType = keyboard, imeAction = ImeAction.Done),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth(),
        keyboardActions = KeyboardActions {
            keyboardOption?.hide()
        },
        singleLine = true
    )

//    Row(
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Icon(
//            painter = painterResource(id = icon),
//            contentDescription = null,
//            tint = MaterialTheme.colors.primaryVariant,
//            modifier = Modifier
//                .size(30.dp)
//        )
//        Spacer(modifier = Modifier.width(20.dp))
//        Text(
//            text = desc,
//            style = MaterialTheme.typography.body1,
//            color = MaterialTheme.colors.surface
//        )
//    }
}
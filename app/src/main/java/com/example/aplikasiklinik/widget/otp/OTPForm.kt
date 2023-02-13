package com.example.aplikasiklinik.widget.otp

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aplikasiklinik.view.navigation.Routes

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OTPForm(
    navController: NavController,
    string: (String) -> Unit
) {
    var otpValue by remember {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val focusRequester = FocusRequester()

    string.invoke(otpValue)
    BasicTextField(value = otpValue,
        onValueChange = {
            if (it.length <= 4) {
                otpValue = it
            }
            if (it.length == 4) {
                navController.navigate(Routes.Home.route)
            }

        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Done
        ),
        textStyle = MaterialTheme.typography.body1.copy(Color.Black),
        cursorBrush = SolidColor(MaterialTheme.colors.onSurface),
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                repeat(4) { index ->
                    val char = when {
                        index >= otpValue.length -> ""
                        else -> otpValue[index].toString()
                    }

                    Text(
                        text = char,
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .width(60.dp)
                            .background(MaterialTheme.colors.primary)
                            .padding(top = 12.dp, bottom = 12.dp, start = 4.dp, end = 4.dp),
                        style = MaterialTheme.typography.h2,
                        fontSize = 30.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                }
            }
        },
        modifier = Modifier
            .focusRequester(focusRequester)
            .onFocusChanged {
                if (it.isFocused) {
                    keyboardController?.show()
                }
            },
        keyboardActions = KeyboardActions {
            keyboardController?.hide()
        }
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}


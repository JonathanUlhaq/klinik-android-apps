package com.example.aplikasiklinik.widget.register

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aplikasiklinik.R
import com.example.aplikasiklinik.components.DatePicker
import com.example.aplikasiklinik.components.DisableInput
import com.example.aplikasiklinik.components.OutlinedTextFields
import com.example.aplikasiklinik.components.RegistDatePicker
import com.example.aplikasiklinik.view.register.RegisterViewModel

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun RegisForm(
    nik: MutableState<String>,
    hide: MutableState<Boolean>,
    name: MutableState<String>,
    context: Context,
    date: MutableState<String>,
    address: MutableState<String>,
    phone: MutableState<String>,
    isError:MutableState<Boolean>,
    detectFirst:MutableState<Boolean>,
    stillBlank:MutableState<Boolean>,
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
    val nikValidator = remember {
        mutableStateOf(false)
    }

    val pasienSelected = remember {
        mutableStateOf(false)
    }

    val currentSelected = remember {
        mutableStateOf(0)
    }

    val listJenisPasien = listOf(
        "Pasien Non BPJS",
        "Pasien BPJS"
    )

    val firstString = remember {
        mutableStateOf("")
    }



    if (phone.value.isNotEmpty()) {
        firstString.value = phone.value.first().toString()
        detectFirst.value = firstString.value == "0"
    }

    val nikFocus = remember { mutableStateOf(false) }

    nikValidator.value = nik.value.length < 14

    Column {

        Text(text = "Jenis Pasien",
            style = MaterialTheme.typography.h1,
            color = Color.White,
            fontSize = 12.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Surface(
            color = Color.Transparent,
            border = BorderStroke(2.dp, Color.White),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .height(38.dp)
                .width(210.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                listJenisPasien.forEachIndexed {index,string ->
                    pasienSelected.value = index == currentSelected.value
                    val colorFont by animateColorAsState(targetValue = if (pasienSelected.value) MaterialTheme.colors.primary else Color.White.copy(0.5f))
                    val colorSurface by animateColorAsState(targetValue = if (pasienSelected.value) Color.White else Color.Transparent)
                    Surface(
                        color = colorSurface,
                        shape = RoundedCornerShape(12.dp),
                        onClick = {
                            currentSelected.value = index
                        },
                        modifier = Modifier
                            .fillMaxHeight()
                    ) {
                        Text(string,
                            fontSize = 12.sp,
                            color = colorFont,
                            style = MaterialTheme.typography.h1,
                            modifier = Modifier
                                .padding(12.dp))
                    }

                }
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Form Data Diri",
            style = MaterialTheme.typography.h1,
            color = Color.White,
            fontSize = 12.sp)
      AnimatedVisibility(visible = currentSelected.value == 1 ) {
          Column {
              Spacer(modifier = Modifier.height(4.dp))
              OutlinedTextFields(
                  nik,
                  label = stringResource(id = R.string.nik),
                  R.drawable.nik_icon,
                  eventFocus = {
                      hide.value = true
                      nikFocus.value = true
                  },
                  keyboardType = KeyboardType.Number,
                  onDone = {
                      namaFocus.requestFocus()
                  },
                  error = if (nikFocus.value) nikValidator.value else false,
                  isNIK = true,
                  trailingIcon = {
                      AnimatedVisibility(visible = (nik.value.isNotEmpty())) {
                          IconButton(onClick = { nik.value = "" }) {
                              Icon(painter = painterResource(id = R.drawable.close_icon),
                                  contentDescription = null,
                                  tint = Color.White,
                                  modifier = Modifier
                                      .size(16.dp)
                              )
                          }
                      }
                  }
              ) {
                  hide.value = false
              }
              Spacer(modifier = Modifier.height(4.dp))
              if (nikFocus.value) {
                  AnimatedVisibility(visible = nikValidator.value) {
                      Text(text = "* masukkan Nomor BPJS 13 digit",
                          style = MaterialTheme.typography.body1,
                          color = MaterialTheme.colors.error)
                  }
              }
          }
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
        },
        trailingIcon = {
            AnimatedVisibility(visible = (name.value.isNotEmpty())) {
                IconButton(onClick = { name.value = "" }) {
                    Icon(painter = painterResource(id = R.drawable.close_icon),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .size(16.dp)
                    )
                }
            }
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
            .focusRequester(alamatFocus),
        trailingIcon = {
            AnimatedVisibility(visible = (address.value.isNotEmpty())) {
                IconButton(onClick = { address.value = "" }) {
                    Icon(painter = painterResource(id = R.drawable.close_icon),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .size(16.dp)
                    )
                }
            }
        }
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
            keyboardType = KeyboardType.Number,
            trailingIcon = {
                AnimatedVisibility(visible = (phone.value.isNotEmpty())) {
                    IconButton(onClick = { phone.value = "" }) {
                        Icon(
                            painter = painterResource(id = R.drawable.close_icon),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .size(16.dp)
                        )
                    }
                }
            },
            isPhone = true,
            modifier = Modifier
                .focusRequester(phoneFocus)
        ) {
            hide.value = false
        }
    }
        Spacer(modifier = Modifier.height(2.dp))
        AnimatedVisibility(visible = stillBlank.value || isError.value) {
            Text(
                "* Cek dan lengkapi semua form isian",
                fontSize = 12.sp,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption
            )
        }
        AnimatedVisibility(visible = detectFirst.value) {
            Text(
                "* Isiikan nomor telepon setelah angka 0",
                fontSize = 12.sp,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption
            )
        }
        AnimatedVisibility(visible = phone.value.length < 10 && phone.value.isNotEmpty()) {
            Text(
                "* Isiikan nomor telepon minimal 10 digit",
                fontSize = 12.sp,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption
            )
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
                .clickable {
                    click.invoke()

                })
    }
    }
}

package com.example.mycompose.ui.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.mycompose.R
import com.example.mycompose.ui.theme.*
import com.google.android.material.badge.BadgeDrawable

@Composable
fun ComposableButton(buttonText: Int, isEnabled: Boolean = false, submit: () -> Unit) {
    Button(
        enabled = isEnabled,
        onClick = submit,
        modifier = Modifier
            .padding(32.dp)
            .fillMaxWidth(0.6f)
            .height(54.dp),
    ) {
        Text(stringResource(id = buttonText))
    }
}

@Composable
fun ComposableInput(
    hint: Int,
    drawableStart: Int? = null,
    drawableEnd: Int? = null,
    getInput: (String) -> Unit
) {
    var textController by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .focusRequester(focusRequester = focusRequester),
        value = textController,
        onValueChange = {
            textController = it
            getInput(textController)
        },
        label = {
            Text(stringResource(id = hint))
        },
        singleLine = true,
        trailingIcon = if (drawableEnd == null) null else {
            { InputIcon(drawableEnd) }
        },
        leadingIcon = if (drawableStart == null) null else {
            { InputIcon(drawableStart) }
        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

@Composable
fun ComposablePasswordInput(
    hint: Int,
    drawableStart: Int? = null,
    getInput: (String) -> Unit
) {
    var textController by remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .focusRequester(focusRequester = focusRequester),
        value = textController,
        onValueChange = {
            textController = it
            getInput(textController)
        },
        label = {
            Text(stringResource(id = hint))
        },
        visualTransformation = if (passwordVisibility.value) VisualTransformation.None
        else PasswordVisualTransformation(),
        singleLine = true,
        trailingIcon = {
            IconButton(onClick = {
                passwordVisibility.value = !passwordVisibility.value
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.password_eye),
                    tint = if (passwordVisibility.value) Teal200 else Color.Gray,
                    contentDescription = ""
                )
            }
        },
        leadingIcon = if (drawableStart == null) null else {
            { InputIcon(drawableStart) }
        },

        )


}


@Composable
fun InputIcon(res: Int) {
    Icon(
        painter = painterResource(id = res),
        contentDescription = "",
        modifier = Modifier
            .width(24.dp)
            .height(24.dp)
    )
}

@Composable
fun ComposableText(text: Int, isUnderLined: Boolean = false, isClickable: Boolean = false) {

    Text(
        text = stringResource(id = text),
        textDecoration = if (isUnderLined) TextDecoration.Underline else TextDecoration.None,

        )
}
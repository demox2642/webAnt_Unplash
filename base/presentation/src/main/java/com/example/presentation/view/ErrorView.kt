package com.example.presentation.view

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.base.presentation.R
import com.example.presentation.theme.AppTheme

@Composable
@Preview(showBackground = true)
private fun PreviewErrorView() {
    ErrorView()
}

@Composable
fun ErrorView(
    @StringRes title: Int = R.string.error_def_title,
    @StringRes error: Int = R.string.error_def,
    message: String? = null,
) {
    Column(modifier = Modifier.width(127.dp)) {
        Image(
            modifier = Modifier.padding(horizontal = 27.dp),
            painter = painterResource(id = R.drawable.ic_logo_grey),
            contentDescription = "logo_grey",
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            if (message == null) {
                Text(text = stringResource(id = title), style = AppTheme.typography.body1, color = AppTheme.colors.gray)
            } else {
                Text(text = "Error!!!", style = AppTheme.typography.body1, color = AppTheme.colors.gray)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (message == null) {
            Text(
                text = stringResource(id = error),
                style = AppTheme.typography.caption,
                color = AppTheme.colors.gray,
                textAlign = TextAlign.Center,
            )
        } else {
            Text(
                text = message,
                style = AppTheme.typography.caption,
                color = AppTheme.colors.gray,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun AlertDialog(
    modifier: Modifier = Modifier,
    message: String,
    closeDialog: () -> Unit,
) {
    Dialog(
        onDismissRequest = {},
        content = {
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(15.dp),
                elevation = 8.dp,
            ) {
                Column(
                    modifier
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Image(
                        modifier = Modifier.padding(horizontal = 27.dp),
                        painter = painterResource(id = R.drawable.ic_logo_grey),
                        contentDescription = "logo_grey",
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "Error!!!",
                            style = AppTheme.typography.body1,
                            color = AppTheme.colors.gray,
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = message,
                        style = AppTheme.typography.caption,
                        color = AppTheme.colors.gray,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Divider(
                        modifier = Modifier.fillMaxWidth().height(1.dp),
                        color = Color.Black,
                    )
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        TextButton(
                            onClick = closeDialog,
                        ) {
                            Text(
                                text = stringResource(id = R.string.close),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                                color = AppTheme.colors.errorRed,
                            )
                        }
                    }
                }
            }
        },
    )
}

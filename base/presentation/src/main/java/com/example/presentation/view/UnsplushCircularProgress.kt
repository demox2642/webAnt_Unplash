package com.example.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.base.presentation.R
import com.example.presentation.theme.AppTheme

@Composable
@Preview(showBackground = true)
private fun UnsplushCircularProgress() {
    UnsplushProgressCircular()
}

@Composable
fun UnsplushProgressCircular()  {
    Column {
        CircularProgressIndicator(
            color = AppTheme.colors.gray,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = stringResource(id = R.string.load), style = AppTheme.typography.body1, color = AppTheme.colors.gray)
    }
}

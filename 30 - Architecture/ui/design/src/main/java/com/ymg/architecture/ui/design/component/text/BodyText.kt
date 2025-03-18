package com.ymg.architecture.ui.design.component.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.ymg.architecture.ui.design.typography.customTypogrpahy

@Composable
fun Body_1_BoldText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = 16.sp,
        letterSpacing = TextUnit.Unspecified,
        textDecoration = null,
        textAlign = textAlign,
        lineHeight = 23.68.sp,
        overflow = overflow,
        softWrap = true,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = null,
        style = MaterialTheme.customTypogrpahy.pretendardBoldTextStyle
    )
}

@Composable
fun Body_1_MediumText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = 16.sp,
        letterSpacing = TextUnit.Unspecified,
        textDecoration = null,
        textAlign = textAlign,
        lineHeight = 23.68.sp,
        overflow = overflow,
        softWrap = true,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = null,
        style = MaterialTheme.customTypogrpahy.pretendardMediumTextStyle
    )
}

@Composable
fun Body_2_BoldText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = 14.sp,
        letterSpacing = TextUnit.Unspecified,
        textDecoration = null,
        textAlign = textAlign,
        lineHeight = 20.72.sp,
        overflow = overflow,
        softWrap = true,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = null,
        style = MaterialTheme.customTypogrpahy.pretendardBoldTextStyle
    )
}

@Composable
fun Body_2_MediumText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = 14.sp,
        letterSpacing = TextUnit.Unspecified,
        textDecoration = null,
        textAlign = textAlign,
        lineHeight = 20.72.sp,
        overflow = overflow,
        softWrap = true,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = null,
        style = MaterialTheme.customTypogrpahy.pretendardMediumTextStyle
    )
}

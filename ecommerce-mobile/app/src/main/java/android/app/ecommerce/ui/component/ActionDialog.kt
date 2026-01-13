package android.app.ecommerce.ui.component

import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ActionDialog(
    show: Boolean,
    title: String,
    message: String,
    confirmText: String,
    cancelText: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    onDismiss: () -> Unit
) {
    if (show) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(title) },
            text = { Text(message) },
            confirmButton = {
                Text(
                    text = confirmText,
                    modifier = Modifier
                        .padding(12.dp)
                        .clickable {
                            onConfirm()
                            onDismiss()
                        }
                )
            },
            dismissButton = {
                Text(
                    text = cancelText,
                    modifier = Modifier
                        .padding(12.dp)
                        .clickable {
                            onCancel()
                            onDismiss()
                        }
                )
            }
        )
    }
}


@Preview
@Composable
fun ActionDialogPreview() {
    ActionDialog(
        show = true,
        title = "Xác nhận đơn hàng",
        message = "Bạn có chắc muốn xác nhận đơn này?",
        confirmText = "Xác nhận",
        cancelText = "Hủy",
        onConfirm = {},
        onCancel = {},
        onDismiss = {}
    )
}


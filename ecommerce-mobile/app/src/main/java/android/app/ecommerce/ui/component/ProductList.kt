package android.app.ecommerce.ui.component

import android.app.ecommerce.R
import android.app.ecommerce.data.fakedata.FakeData
import android.app.ecommerce.data.model.Product
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ProductList(productsList: List<Product>) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "New Arrival",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "View All",
                fontSize = 13.sp,
                color = Color(0xFF8F959E)
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(productsList) { product ->
                ProductItem(product)
            }
        }
    }
}

@Composable
fun ProductListColumn(products : List<Product>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
//            .verticalScroll(scrollState), // scroll toàn bộ
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "New Arrival", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = "View All", fontSize = 14.sp, color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Các Row 2 cột
        products.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rowItems.forEach { product ->
                    ProductItem(
                        product,
//                        modifier = Modifier.weight(1f)
                    )
                }
                // Nếu row còn 1 item, thêm Spacer để cân layout
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }

        // Footer
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Footer Text", fontSize = 16.sp, color = Color.Gray)
    }
}

@Composable
@Preview
fun ProductListColumnPreview() {
    ProductListColumn(FakeData.productList)
}

@Composable
@Preview
fun ProductListPreview() {
    ProductList(FakeData.productList)
}

@Composable
fun ProductItem(
    product: Product
) {
    Box(
        modifier = Modifier
            .width(160.dp)
            .height(257.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF5F6FA)) // nền nhẹ, tạo cảm giác thẻ
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(203.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Image(
                    painterResource(R.drawable.ic_launcher_background),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )

                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .size(28.dp)
                        .align(Alignment.TopEnd)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_favorite_border),
                        contentDescription = "Favorite",
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.name,
                fontSize = 12.sp,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${product.price}₫",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF9775FA)
            )
        }
    }
}


@Composable
@Preview
fun ProductItemPreview() {
    ProductItem(FakeData.product)
}
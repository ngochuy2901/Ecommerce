package android.app.ecommerce.ui.composable

import android.app.ecommerce.data.fakedata.BrandFakeData
import android.app.ecommerce.data.model.Brand
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BrandList(brandsList: List<Brand>) {

}

@Composable
@Preview
fun BrandListPreview() {
    BrandList(BrandFakeData.brandList)
}

@Composable
fun BrandItem(brand: Brand) {

}

@Composable
@Preview
fun BrandItemPreview() {
    BrandItem(BrandFakeData.brand)
}
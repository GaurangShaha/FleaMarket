package com.flea.market.product.repository

import com.flea.market.common.remote.request
import com.flea.market.product.remote.entity.ProductDetailsEntity
import com.flea.market.product.remote.source.ProductRemoteSource

@Suppress("MaxLineLength")
internal class ProductRepositoryImpl(
    private val productRemoteSource: ProductRemoteSource
) : ProductRepository {
    private val productImageList: List<List<String>> = listOf(
        listOf(
            "https://images.asos-media.com/products/fjallraven-foldsack-no1-backpack-16l/6044196-3?wid=720",
            "https://images.asos-media.com/products/fjallraven-foldsack-no1-backpack-16l/6044196-1-blue?wid=720",
            "https://images.asos-media.com/products/fjallraven-foldsack-no1-backpack-16l/6044196-2?wid=720",
            "https://images.asos-media.com/products/fjallraven-foldsack-no1-backpack-16l/6044196-4?wid=720",
        ), listOf(
            "https://m.media-amazon.com/images/I/81i4reljsVL._UY879_.jpg",
            "https://m.media-amazon.com/images/I/9106+NprbpL._UY879_.jpg",
            "https://m.media-amazon.com/images/I/71H8Et1sSrL._UY879_.jpg"
        ), listOf(
            "https://www.rlmedia.io/is/image/PoloGSI/s7-1415622_alternate10?\$rl_df_pdp_5_7_a10\$",
            "https://www.rlmedia.io/is/image/PoloGSI/s7-1415622_lifestyle?\$rl_df_pdp_5_7_lif\$",
            "https://www.rlmedia.io/is/image/PoloGSI/s7-1415622_alternate3?\$rl_df_pdp_5_7\$",
            "https://www.rlmedia.io/is/image/PoloGSI/s7-1415622_alternate4?\$rl_df_pdp_5_7\$",
            "https://www.rlmedia.io/is/image/PoloGSI/s7-1415622_alternate5?\$rl_df_pdp_5_7\$"
        ), listOf(
            "https://img.ltwebstatic.com/images3_pi/2021/09/30/16329655407502f71cd7b37d0f04ba5eb436d470f3_thumbnail_600x.webp",
            "https://img.ltwebstatic.com/images3_pi/2021/09/30/16329655323e00b730476e9eed696603fc8c15008c_thumbnail_600x.jpg",
            "https://img.ltwebstatic.com/images3_pi/2021/09/30/1632965535f9570b060dd6374246322b015a45ad72_thumbnail_600x.webp"
        ), listOf(
            "https://img.fruugo.com/product/6/68/253132686_max.jpg",
            "https://img.fruugo.com/product/2/67/253132672_max.jpg"
        ), listOf(
            "https://www.ourosjewels.com/cdn/shop/products/round_eternity_wedding_band_1_1140x1140.webp?v=1672058795",
            "https://www.ourosjewels.com/cdn/shop/products/round_eternity_wedding_band_6_1140x1140.webp?v=1688184050",
            "https://www.ourosjewels.com/cdn/shop/products/round_eternity_wedding_band_3_150x150_crop_center.webp?v=1688184050",
        ), listOf(
            "https://i.etsystatic.com/25136990/r/il/f8326c/3104554178/il_1588xN.3104554178_3lfc.jpg",
            "https://i.etsystatic.com/25136990/r/il/c3a540/3104558198/il_1588xN.3104558198_6n1w.jpg",
            "https://i.etsystatic.com/25136990/r/il/adc7e9/3104558710/il_1588xN.3104558710_4v4q.jpg",
            "https://i.etsystatic.com/25136990/r/il/d58b91/3152278977/il_1588xN.3152278977_edfm.jpg"
        ), listOf(
            "https://i.etsystatic.com/38041699/r/il/b4ea6f/4936136208/il_1588xN.4936136208_eoty.jpg",
            "https://i.etsystatic.com/38041699/r/il/62bed1/4936136210/il_1588xN.4936136210_4x2z.jpg"
        ), listOf(
            "https://cdn11.bigcommerce.com/s-7lx8j4pyez/images/stencil/400x500/products/61706/616916/816Cyv4kf0L._AC_SX679___70264.1657035378.jpg?c=1",
            "https://cdn11.bigcommerce.com/s-7lx8j4pyez/images/stencil/400x500/products/61706/616914/71Rhb-i5qtL._AC_SX679___71185.1657035378.jpg?c=1",
            "https://cdn11.bigcommerce.com/s-7lx8j4pyez/images/stencil/400x500/products/61706/616915/91S1naupylL._AC_SX679___58507.1657035378.jpg?c=1",
            "https://cdn11.bigcommerce.com/s-7lx8j4pyez/images/stencil/400x500/products/61706/616917/81_ofbn5zdL._AC_SX679___62301.1657035378.jpg?c=1",
        ), listOf(
            "https://www.cnet.com/a/img/resize/1145d331fe9a72ea9d24de59b5be8bc9a83fd5c5/hub/2013/05/31/aaf0038b-6797-11e3-846b-14feb5ca9861/DSC_0242.jpg?auto=webp&width=768",
            "https://m.media-amazon.com/images/I/61+5i31KqYL._SL1049_.jpg"
        ), listOf(
            "https://i.ebayimg.com/images/g/JQ0AAOSwQaNhS65O/s-l1600.png",
            "https://i.ebayimg.com/images/g/RvQAAOSw4XlhS65N/s-l1600.png",
            "https://i.ebayimg.com/images/g/fQsAAOSwZ5hhS65O/s-l1600.png"
        ), listOf(
            "https://m.media-amazon.com/images/S/aplus-media/vc/ccf8b83d-cdde-4efc-8e57-b9fd0fb1cb31._CR0,0,300,300_PT0_SX300__.jpg"
        ), listOf(
            "https://www.lifewire.com/thmb/VzYE5cSebl0WcUqmh_rkxnqwuPU=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/Acer_SB220Q_Monitor_01LW4061369_Hero_Final_Square-272181c4e8b04fe2a8e2f949fc5d3228.jpg",
            "https://www.lifewire.com/thmb/QS7xF0tFeTThmms70YSg24jEX0I=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/Acer_SB220Q_Monitor_01LW4061369_2_Final_Horiz-f58201f443c04a0a8422504591b3bbeb.jpg",
            "https://www.lifewire.com/thmb/wb5gLmmEmeag8oCIUHl7o-nEUeM=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/Acer_SB220Q_Monitor_01LW4061369_6_Final_Horiz-cda109c444464d4384aee081cff3443b.jpg"
        ), listOf(
            "https://i.pcmag.com/imagery/reviews/03Qnlt9aC4lMcPMuRP7HiCO-1.fit_lim.size_1620x912.v_1569469928.jpg",
            "https://www.digitaltrends.com/wp-content/uploads/2018/10/samsung-chg90-ultrawide-monitor-review-5490.jpg"
        ), listOf(
            "https://contents.mediadecathlon.com/p2041304/5a3409bc499a45cf80a14ea3dca5ae5a/p2041304.jpg",
            "https://contents.mediadecathlon.com/p2041517/c980a331300cf295a8b8777319eddf90/p2041517.jpg",
            "https://contents.mediadecathlon.com/p2041434/d54d5251a6a4bc743f6fc0cfab86321f/p2041434.jpg",
            "https://contents.mediadecathlon.com/p2041404/c303651079e5d5fc58828a35786de6da/p2041404.jpg"
        ), listOf(
            "https://i5.walmartimages.com/asr/65b722be-da0c-4387-ab94-c8cc24c0d0a1.8727248b314eb882a397d2f6d4afe47b.jpeg",
            "https://i5.walmartimages.com/asr/eba39740-8a5c-4d3d-86a9-5eeb33fe1cfb.07d18fcff6e89435bd0a20c18ac083b2.jpeg",
            "https://i5.walmartimages.com/asr/0fea5ca7-81ab-46f1-af7c-3057c60473d4.bb24dc5537a3399837dda79ecaa40e86.jpeg",
            "https://i5.walmartimages.com/asr/ebed76b0-2b23-45f0-8edd-fb3276cf5a3a.866ef1695dbb391a1644506eab0e00b4.jpeg"
        ), listOf(
            "https://m.media-amazon.com/images/I/51swP87pNoL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/513e+ZwTb5L._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/51bL+Id5EsL._AC_SX679_.jpg"
        ), listOf(
            "https://m.media-amazon.com/images/I/817cdkiSn4L.jpg",
            "https://m.media-amazon.com/images/I/71lOkDyEnLL.jpg",
            "https://m.media-amazon.com/images/I/81gF6aFvMIL.jpg"
        ), listOf(
            "https://rukminim1.flixcart.com/image/832/832/l2tcfbk0/t-shirt/x/y/8/xl-brsts114red-brinns-original-image2skje8gvxxy.jpeg",
            "https://rukminim1.flixcart.com/image/832/832/l2tcfbk0/t-shirt/1/h/o/xl-brsts114red-brinns-original-image2skeey55pen.jpeg?q=70",
            "https://rukminim1.flixcart.com/image/832/832/l2tcfbk0/t-shirt/6/k/u/xl-brsts114red-brinns-original-image2skgkyhkmyf.jpeg?q=70",
            "https://rukminim1.flixcart.com/image/832/832/l2tcfbk0/t-shirt/h/n/6/xl-brsts114red-brinns-original-image2skr6jzuuus.jpeg?q=70"
        ), listOf(
            "https://img.ltwebstatic.com/images3_pi/2021/08/17/162917165267dc2c0bb3806e3dd16fb389d1117887_thumbnail_600x.webp",
            "https://img.ltwebstatic.com/images3_pi/2021/08/17/16291716461ac148eabb8b714db82a8031307a4da9_thumbnail_600x.jpg",
            "https://img.ltwebstatic.com/images3_pi/2021/08/17/16291716493b02f62fb629b6d466cc60431bcbfdf6_thumbnail_600x.webp",
            "https://img.ltwebstatic.com/images3_pi/2021/08/17/162917165473caff843fabc60f1823fd790912121b_thumbnail_600x.webp",
        )
    )

    override suspend fun getProductList() = request(onSuccess = { _, _, productList ->
        productList.map { it.getProductDetailsEntityWithImageList() }
    }) {
        productRemoteSource.getProductList()
    }

    override suspend fun getProductDetails(id: Int) = request(onSuccess = { _, _, productDetails ->
        productDetails.getProductDetailsEntityWithImageList()
    }) {
        productRemoteSource.getProductDetails(id)
    }

    private fun ProductDetailsEntity.getProductDetailsEntityWithImageList() =
        this.copy(imageList = productImageList.getOrElse(id - 1) { _ -> listOf(image) })
}
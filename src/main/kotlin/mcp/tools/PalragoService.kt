package mcp.tools

import org.springframework.ai.tool.annotation.Tool
import org.springframework.stereotype.Service

@Service
class PalragoService {

    @Tool(name = "search_market_product",
        description = "search for products in the marketplace")
    fun searchMarketProduct(productName: String, brandName: String): String {

        val safeProductName = productName?.takeIf { it.isNotBlank() } ?: "상품 테스트"
        val safeBrandName = brandName?.takeIf { it.isNotBlank() } ?: "브랜드 테스트"

        return "상품명은 [$safeProductName] 이며, 브랜드 이름은 [$safeBrandName] 입니다."
    }
}
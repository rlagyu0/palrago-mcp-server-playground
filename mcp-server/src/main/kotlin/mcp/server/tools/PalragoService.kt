package mcp.server.tools

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.ai.tool.annotation.Tool
import org.springframework.ai.tool.annotation.ToolParam
import org.springframework.stereotype.Service

@Service
class PalragoService {

    @Tool(name = "search_market_product",
        description = "search for products in the marketplace",
        returnDirect = true)
    fun searchMarketProduct(
                                    @ToolParam(description = "search product name") productName: String,
                                    @ToolParam(description = "search brand name of search product") brandName: String
                            ): Response {

        val safeProductName = productName?.takeIf { it.isNotBlank() } ?: "상품명 미입력"
        val safeBrandName = brandName?.takeIf { it.isNotBlank() } ?: "브랜드명 미입력"

//        val product = Response(safeProductName, safeBrandName)

//        val json = jacksonObjectMapper().writeValueAsString(product)

        return Response(safeProductName, safeBrandName)
    }

    data class Response(
        val productName: String?,
       val brandName: String?
    )
}
package com.uk.leovegas.wallet.requestmodel;

import com.uk.leovegas.wallet.entity.domain.CurrencyCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request model used to add the wallet to the player with zero balance
 *
 * @author Anoop Abraham
 *
 */
@ApiModel(description = "Add the wallet to the Player")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerWalletRequest {

    /** The Player id */
    @ApiModelProperty(notes = "Player id", required = true, position = 1)
    private Long playerId;


    /** The currency Code */
    @ApiModelProperty(notes = "The currency Code", required = true, position = 2)
    private CurrencyCode currencyCode;

}

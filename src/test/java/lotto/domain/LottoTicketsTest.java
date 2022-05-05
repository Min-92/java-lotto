package lotto.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;

import static lotto.domain.LottoNumbers.generateLottoNumbers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTicketsTest {
    @ParameterizedTest
    @NullAndEmptySource
    void LottoTickets는_lottoTickets없이_생성시_예외를_발생시킨다(List<LottoTicket> lottoTickets) {
        assertThatThrownBy(() -> {
            new LottoTickets(lottoTickets);
        }).isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void check는_lottoPrizes를_반환한다() {
        List<LottoPrize> actualPrizes = new LottoTickets(
                List.of(
                        new LottoTicket(generateLottoNumbers(1, 6)),
                        new LottoTicket(generateLottoNumbers(2, 7))))
                .check(generateLottoNumbers(1, 6), new LottoNumber(7)).getLottoPrizes();
        List<LottoPrize> expectedPrizes = List.of(LottoPrize.FIRST, LottoPrize.SECOND);


        assertThat(actualPrizes).isEqualTo(expectedPrizes);
    }
}

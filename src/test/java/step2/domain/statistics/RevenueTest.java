package step2.domain.statistics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RevenueTest {
    @Test
    @DisplayName("등수에 따라 수익 금액을 더한다")
    void profitAddTest() {
        final Revenue actual = new Revenue(0);
        actual.add(Rank.FIFTH);
        actual.add(Rank.THIRD);

        final long profit = Rank.FIFTH.winnings + Rank.THIRD.winnings;
        final Revenue expected = new Revenue(profit);

        System.out.println(expected);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("5등 1개, 미당첨 13개, 총 14개. 기존 수익금액에 구매 갯수로 수익률을 계산")
    void calculateProfitRateTest() {
        final Revenue revenue = new Revenue();
        revenue.add(Rank.FIFTH);
        final ProfitRate actual = revenue.calculateProfitRate(new Amount(14));

        final double expectedProfitRate = Rank.FIFTH.winnings / (double) 14000;
        final ProfitRate expected = new ProfitRate(expectedProfitRate);

        System.out.println(actual);
        System.out.println(expected);
        assertThat(actual).isEqualTo(expected);
    }
}
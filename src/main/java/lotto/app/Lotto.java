package lotto.app;

import lotto.domain.Amount;
import lotto.domain.LottoPrizes;
import lotto.domain.LottoResult;
import lotto.domain.LottoStatistics;
import lotto.domain.LottoTickets;
import lotto.domain.LottoVendingMachine;
import lotto.domain.TicketCount;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Lotto {
    private final LottoVendingMachine lottoVendingMachine;

    public Lotto(LottoVendingMachine lottoVendingMachine) {
        this.lottoVendingMachine = lottoVendingMachine;
    }

    public void start() {
        Amount purchaseAmount = generatePurchaseAmount();
        lottoVendingMachine.insert(purchaseAmount);

        LottoTickets lottoTickets = vendLottoTickets();
        OutputView.printTickets(lottoTickets);

        LottoResult lottoResult = generateLottoResult();
        LottoPrizes lottoPrizes = lottoResult.check(lottoTickets);

        OutputView.printStatistics(LottoStatistics.from(lottoPrizes));
    }

    private Amount generatePurchaseAmount() {
        return InputView.inputPurchaseAmount();
    }

    private LottoTickets vendLottoTickets() {
        TicketCount manualTicketsCount = InputView.inputManualTicketsCount();

        if (manualTicketsCount.isZero()) {
            return vendAutomaticTickets();
        }

        return vendManualTickets(manualTicketsCount)
                .add(vendAutomaticTickets());
    }

    private LottoTickets vendManualTickets(TicketCount manualTicketsCount) {
        return lottoVendingMachine.vend(
                InputView.inputLottoNumbers(manualTicketsCount)
        );
    }

    private LottoTickets vendAutomaticTickets() {
        return lottoVendingMachine.vend();
    }

    private LottoResult generateLottoResult() {
        return InputView.inputLottoResult();
    }
}

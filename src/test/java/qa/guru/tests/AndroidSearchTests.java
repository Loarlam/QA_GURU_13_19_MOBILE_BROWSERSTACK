package qa.guru.tests;

import com.codeborne.selenide.CollectionCondition;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class AndroidSearchTests extends TestBase {
    @Test
    @Owner("Loarlam")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Запуск мобильных автотестов на сайте browserstack.com для поиска в вики значения \"dyskinesia\"")
    void searchTextWikiInSearchSrc() {
        step("Поиск в поисковой строке значения dyskinesia", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))
                    .sendKeys("Dyskinesia");
        });
        step("Проверка найденного контента dyskinesia", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @Owner("Loarlam")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Запуск мобильных автотестов на сайте browserstack.com для поиска в вики значения \"aplasia\"")
    void searchTextWikiInSearchContatiner() {
        step("Поиск в search_container значения Aplasia", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Aplasia");
        });
        step("Проверка найденного контента Aplasia", () -> {
            $$(AppiumBy.id("org.wikipedia.alpha:id/search_results_list")).shouldHave(
                    CollectionCondition.sizeGreaterThan(0));
        });
        step("Открыть страницу Aplasia", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).click();
        });
    }
}

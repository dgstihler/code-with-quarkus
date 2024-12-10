package com.ismoke.test.archunit;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;

@AnalyzeClasses(packages = "com.ismoke") // Base do seu projeto
public class ArchitectureTest {

    private final JavaClasses classes = new ClassFileImporter().importPackages("com.ismoke")
        .that(DescribedPredicate.not(resideInAPackage("..test..")));

    @Test
    public void dominioNaoDeveDependerDeOutrasCamadas() {
        ArchRuleDefinition.classes()
            .that().resideInAPackage("..domain..")
            .should().onlyDependOnClassesThat()
            .resideInAnyPackage(
                "java..",             // Dependências padrão do Java
                "com.ismoke.domain.." // Permite dependências internas do domínio
            )
            .check(classes);
    }

    @Test
    public void aplicacaoDeveDependerSomenteDoDominio() {
        ArchRuleDefinition.classes()
            .that().resideInAPackage("..application..")
            .should().onlyDependOnClassesThat()
            .resideInAnyPackage(
                "java..",
                "..test..",
                "jakarta..",
                "com.ismoke.application..",
                "com.ismoke.domain.." // Permite dependências no domínio
            )
            .check(classes);
    }

    @Test
    public void infraestruturaNaoDeveSerAcessadaPorOutrasCamadas() {
        ArchRuleDefinition.noClasses()
            .that().resideOutsideOfPackage("..infrastructure..")
            .should().accessClassesThat()
            .resideInAPackage("..infrastructure..")
            .check(classes);
    }

    @Test
    public void apresentacaoDeveSoDependerDeAplicacao() {
        ArchRuleDefinition.classes()
            .that().resideInAPackage("..presentation..")
            .should().onlyDependOnClassesThat()
            .resideInAnyPackage(
                "java..",
                "..test..",
                "jakarta..",
                "com.ismoke.presentation..",
                "com.ismoke.application.." // Permite dependências na camada de aplicação
            )
            .check(classes);
    }
}


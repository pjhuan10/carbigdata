package com.example.carbigdata;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.ArchTest;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ArchitectureTest {

    @ArchTest
    static void domainLayerShouldNotDependOnOtherLayers(JavaClasses classes) {
        noClasses()
                .that().resideInAnyPackage("com.example.carbigdata.domain..")
                .should().dependOnClassesThat().resideOutsideOfPackage("com.example.carbigdata.domain..")
                .check(classes);
    }

    @ArchTest
    static void applicationLayerShouldOnlyDependOnDomainAndAdapters(JavaClasses classes) {
        noClasses()
                .that().resideInAnyPackage("com.example.carbigdata.application..")
                .should().dependOnClassesThat().resideOutsideOfPackage("com.example.carbigdata.domain..")
                .check(classes);
    }

    @ArchTest
    static void controllersShouldDependOnlyOnApplicationLayer(JavaClasses classes) {
        noClasses()
                .that().resideInAnyPackage("com.example.carbigdata.controller..")
                .should().dependOnClassesThat().resideOutsideOfPackage("com.example.carbigdata.application..")
                .check(classes);
    }

    @ArchTest
    static void repositoriesShouldBeInInfrastructureLayer(JavaClasses classes) {
        noClasses()
                .that().areAnnotatedWith(org.springframework.stereotype.Repository.class)
                .should().resideInAnyPackage("com.example.carbigdata.infrastructure..")
                .check(classes);
    }

    @ArchTest
    static void domainAndInfrastructureShouldNotDependOnEachOther(JavaClasses classes) {
        noClasses()
                .that().resideInAnyPackage("com.example.carbigdata.domain..")
                .should().dependOnClassesThat().resideInAnyPackage("com.example.carbigdata.infrastructure..")
                .check(classes);

        noClasses()
                .that().resideInAnyPackage("com.example.carbigdata.infrastructure..")
                .should().dependOnClassesThat().resideInAnyPackage("com.example.carbigdata.domain..")
                .check(classes);
    }
}

-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-06-2014 a las 00:40:19
-- Versión del servidor: 5.5.32
-- Versión de PHP: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `tiendafrutadb`
--
CREATE DATABASE IF NOT EXISTS `tiendafrutadb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `tiendafrutadb`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
  `cedula` int(11) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  PRIMARY KEY (`cedula`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`cedula`, `nombre`, `telefono`) VALUES
(123, 'Juan', '5422487'),
(1478, 'Pedro', '85697');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE IF NOT EXISTS `factura` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `cliente` int(11) NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`id`, `fecha`, `cliente`, `total`) VALUES
(7, '2014-06-10', 123, 20850),
(8, '2014-06-10', 1478, 22200);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fruits`
--

CREATE TABLE IF NOT EXISTS `fruits` (
  `id` varchar(8) NOT NULL,
  `name` varchar(24) NOT NULL,
  `price` double NOT NULL DEFAULT '22',
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `fruits`
--

INSERT INTO `fruits` (`id`, `name`, `price`, `quantity`) VALUES
('1', 'Manzana', 500, 35),
('2', 'Banano', 750, 45),
('3', 'Pera', 850, 100),
('4', 'Mango', 450, 200),
('5', 'Mandarina', 600, 145);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE IF NOT EXISTS `venta` (
  `idFruta` int(11) NOT NULL DEFAULT '0',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad` int(11) NOT NULL,
  `valorPagado` double NOT NULL,
  `idFactura` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`idFruta`, `id`, `cantidad`, `valorPagado`, `idFactura`) VALUES
(1, 11, 12, 6000, 7),
(2, 12, 5, 3750, 7),
(3, 13, 4, 3400, 7),
(1, 14, 7, 3500, 7),
(5, 15, 7, 4200, 7),
(3, 16, 12, 10200, 8),
(2, 17, 10, 7500, 8),
(4, 18, 10, 4500, 8);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

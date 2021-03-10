import React from 'react';
import * as AiIcons from 'react-icons/ai';
import * as IoIcons from 'react-icons/io';
import * as GiIcons from 'react-icons/gi';


export const SidebarData = [
    {
        title: 'Home',
        path: '/',
        icon: <AiIcons.AiFillHome />,
        cName: 'nav-text'
    },
    {
        title: 'Recept',
        path: '/recipes',
        icon: <IoIcons.IoIosPaper />,
        cName: 'nav-text'
    },
    {
        title: 'Kostnad',
        path: '/costs',
        icon: <GiIcons.GiMoneyStack />,
        cName: 'nav-text'
    },
    {
        title: 'Lägga till recept',
        path: '/addrecipes',
        icon: <AiIcons.AiOutlineFileAdd />,
        cName: 'nav-text'
    }
]
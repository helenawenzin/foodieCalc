import React from 'react';
import * as AiIcons from 'react-icons/ai';
import * as IoIcons from 'react-icons/io';
import * as GiIcons from 'react-icons/gi';
import * as RiIcons from 'react-icons/ri';


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
        title: 'Lägga till',
        path: '/adding',
        icon: <AiIcons.AiOutlineFileAdd />,

        iconClosed: <RiIcons.RiArrowDownSFill />,
        iconOpened: <RiIcons.RiArrowUpFill />,

        subNav: [
            {
                title: 'Recept',
                path: '/adding/recipe',
                icon: <IoIcons.IoMdAdd />
            },
            {
                title: 'Kategori',
                path: '/adding/category',
                icon: <IoIcons.IoMdAdd />
            },
             {
                title: 'Ingrediens',
                path: '/adding/ingredient',
                icon: <IoIcons.IoMdAdd  />
              }
        ],
        cName: 'nav-text'
    }
]